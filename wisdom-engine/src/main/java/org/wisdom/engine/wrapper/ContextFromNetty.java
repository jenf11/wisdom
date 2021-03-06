/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wisdom.engine.wrapper;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.content.BodyParser;
import org.wisdom.api.cookies.Cookie;
import org.wisdom.api.cookies.Cookies;
import org.wisdom.api.cookies.FlashCookie;
import org.wisdom.api.cookies.SessionCookie;
import org.wisdom.api.http.*;
import org.wisdom.api.router.Route;
import org.wisdom.engine.server.ServiceAccessor;
import org.wisdom.engine.wrapper.cookies.CookieHelper;
import org.wisdom.engine.wrapper.cookies.FlashCookieImpl;
import org.wisdom.engine.wrapper.cookies.SessionCookieImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * An implementation from the Wisdom HTTP context based on servlet objects.
 * Not Thread Safe !
 */
public class ContextFromNetty implements Context {

    private static AtomicLong ids = new AtomicLong();
    private final long id;
    private final HttpRequest httpRequest;
    private final ServiceAccessor services;
    private final FlashCookie flashCookie;
    private final SessionCookie sessionCookie;
    private final QueryStringDecoder queryStringDecoder;
    private /*not final*/ Route route;
    /**
     * the request object, created lazily.
     */
    private RequestFromNetty request;
    /**
     * Attribute from the body.
     */
    private Map<String, List<String>> attributes = Maps.newHashMap();
    /**
     * List of uploaded files.
     */
    private List<FileItemFromNetty> files = Lists.newArrayList();

    /**
     * The raw body.
     */
    private String raw;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextFromNetty.class);


    /**
     * Creates a new context.
     *
     * @param accessor a structure containing the used services.
     * @param ctxt     the channel handler context.
     * @param req      the incoming HTTP Request.
     */
    public ContextFromNetty(ServiceAccessor accessor, ChannelHandlerContext ctxt, HttpRequest req) {
        id = ids.getAndIncrement();
        httpRequest = req;
        services = accessor;
        queryStringDecoder = new QueryStringDecoder(httpRequest.getUri());
        request = new RequestFromNetty(this, ctxt, httpRequest);

        flashCookie = new FlashCookieImpl(accessor.getConfiguration());
        sessionCookie = new SessionCookieImpl(accessor.getCrypto(), accessor.getConfiguration());
        sessionCookie.init(this);
    }

    /**
     * A http content type should contain a character set like
     * "application/json; charset=utf-8".
     * <p/>
     * If you only want to get "application/json" you can use this method.
     * <p/>
     * See also: http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7.1
     *
     * @param rawContentType "application/json; charset=utf-8" or "application/json"
     * @return only the contentType without charset. Eg "application/json"
     */
    public static String getContentTypeFromContentTypeAndCharacterSetting(String rawContentType) {

        if (rawContentType.contains(";")) {
            return rawContentType.split(";")[0];
        } else {
            return rawContentType;
        }

    }

    public void decodeContent(HttpRequest req, HttpContent content, HttpPostRequestDecoder decoder) {
        // Determine whether the content is chunked.
        boolean readingChunks = HttpHeaders.isTransferEncodingChunked(req);
        // Offer the content to the decoder.
        if (readingChunks) {
            // If needed, read content chunk by chunk.
            decoder.offer(content);
            readHttpDataChunkByChunk(decoder);
        } else {
            // Else, read content.
            if (content.content().isReadable()) {
                this.raw = content.content().toString(CharsetUtil.UTF_8);
            }
            decoder.offer(content);
            try {
                for (InterfaceHttpData data : decoder.getBodyHttpDatas()) {
                    readAttributeOrFile(data);
                }
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException e) {
                LOGGER.debug("Error when decoding content, not enough data", e);
            }
        }
    }

    /**
     * Example of reading request by chunk and getting values from chunk to chunk
     */
    private void readHttpDataChunkByChunk(HttpPostRequestDecoder decoder) {
        try {
            while (decoder.hasNext()) {
                InterfaceHttpData data = decoder.next();
                if (data != null) {
                    try {
                        // new value
                        readAttributeOrFile(data);
                    } finally {
                        // Do not release the data if it's a file, we released it once everything is done.
                        if (data.getHttpDataType() != InterfaceHttpData.HttpDataType.FileUpload) {
                            data.release();
                        }
                    }
                }

            }
        } catch (HttpPostRequestDecoder.EndOfDataDecoderException e) {
            LOGGER.debug("Error when decoding content, end of data reached", e);
        }
    }

    private void readAttributeOrFile(InterfaceHttpData data) {
        if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
            Attribute attribute = (Attribute) data;
            String value;
            try {
                String name = attribute.getName();
                value = attribute.getValue();
                List<String> values = attributes.get(name);
                if (values == null) {
                    values = new ArrayList<>();
                    attributes.put(name, values);
                }
                values.add(value);
            } catch (IOException e) {
                LOGGER.warn("Error while reading attributes", e);
            }
        } else {
            if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
                FileUpload fileUpload = (FileUpload) data;
                if (fileUpload.isCompleted()) {
                    files.add(new FileItemFromNetty(fileUpload));
                } else {
                    LOGGER.warn("Un-complete file upload");
                }
            }
        }
    }

    /**
     * The context id (unique).
     */
    @Override
    public Long id() {
        return id;
    }

    /**
     * Returns the current request.
     */
    @Override
    public Request request() {
        return request;
    }

    /**
     * Returns the current response.
     */
    @Override
    public Response response() {
        return null;
    }

    /**
     * Returns the path that the controller should act upon.
     * <p/>
     * For instance in servlets you could have something like a context prefix.
     * /myContext/app
     * <p/>
     * If your route only defines /app it will work as the requestpath will
     * return only "/app". A context path is not returned.
     * <p/>
     * It does NOT decode any parts of the url.
     * <p/>
     * Interesting reads: -
     * http://www.lunatech-research.com/archives/2009/02/03/
     * what-every-web-developer-must-know-about-url-encoding -
     * http://stackoverflow
     * .com/questions/966077/java-reading-undecoded-url-from-servlet
     *
     * @return The the path as seen by the server. Does exclude any container
     * set context prefixes. Not decoded.
     */
    @Override
    public String path() {
        return request().path();
    }

    /**
     * Returns the flash cookie. Flash cookies only live for one request. Good
     * uses are error messages to display. Almost everything else is bad use of
     * Flash Cookies.
     * <p/>
     * A FlashCookie is usually not signed. Don't trust the content.
     *
     * @return the flash cookie of that request.
     */
    @Override
    public FlashCookie flash() {
        return flashCookie;
    }

    /**
     * Returns the client side session. It is a cookie. Therefore you cannot
     * store a lot of information inside the cookie. This is by intention.
     * <p/>
     * If you have the feeling that the session cookie is too small for what you
     * want to achieve thing again. Most likely your design is wrong.
     *
     * @return the Session of that request / response cycle.
     */
    @Override
    public SessionCookie session() {
        return sessionCookie;
    }

    /**
     * Get cookie from context.
     *
     * @param cookieName Name of the cookie to retrieve
     * @return the cookie with that name or null.
     */
    @Override
    public Cookie cookie(String cookieName) {
        return request().cookie(cookieName);
    }

    /**
     * Checks whether the context contains a given cookie.
     *
     * @param cookieName Name of the cookie to check for
     * @return {@code true} if the context has a cookie with that name.
     */
    @Override
    public boolean hasCookie(String cookieName) {
        return request().cookie(cookieName) != null;
    }

    /**
     * Get all cookies from the context.
     *
     * @return the cookie with that name or null.
     */
    @Override
    public Cookies cookies() {
        return request().cookies();
    }

    /**
     * Get the context path on which the application is running
     *
     * @return the context-path with a leading "/" or "" if running on root
     */
    @Override
    public String getContextPath() {
        // TODO this does make sense only behind a bridge right ?
        return "";
    }

    /**
     * Get the parameter with the given key from the request. The parameter may
     * either be a query parameter, or in the case of form submissions, may be a
     * form parameter.
     * <p/>
     * When the parameter is multivalued, returns the first value.
     * <p/>
     * The parameter is decoded by default.
     *
     * @param name The key of the parameter
     * @return The value, or null if no parameter was found.
     * @see #parameterMultipleValues
     */
    @Override
    public String parameter(String name) {
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        if (parameters != null && parameters.containsKey(name)) {
            // Return only the first one.
            return parameters.get(name).get(0);
        }
        return null;
    }

    @Override
    public Map<String, List<String>> attributes() {
        return attributes;
    }

    /**
     * Get the parameter with the given key from the request. The parameter may
     * either be a query parameter, or in the case of form submissions, may be a
     * form parameter.
     * <p/>
     * The parameter is decoded by default.
     *
     * @param name The key of the parameter
     * @return The values, possibly an empty list.
     */
    @Override
    public List<String> parameterMultipleValues(String name) {
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        if (parameters != null && parameters.containsKey(name)) {
            return parameters.get(name);
        }
        return new ArrayList<String>();
    }

    /**
     * Same like {@link #parameter(String)}, but returns given defaultValue
     * instead of null in case parameter cannot be found.
     * <p/>
     * The parameter is decoded by default.
     *
     * @param name         The name of the post or query parameter
     * @param defaultValue A default value if parameter not found.
     * @return The value of the parameter of the defaultValue if not found.
     */
    @Override
    public String parameter(String name, String defaultValue) {
        String parameter = parameter(name);
        if (parameter == null) {
            return defaultValue;
        }
        return parameter;
    }

    /**
     * Same like {@link #parameter(String)}, but converts the parameter to
     * Integer if found.
     * <p/>
     * The parameter is decoded by default.
     *
     * @param name The name of the post or query parameter
     * @return The value of the parameter or null if not found.
     */
    @Override
    public Integer parameterAsInteger(String name) {
        String parameter = parameter(name);
        try {
            return Integer.parseInt(parameter);
        } catch (Exception e) {  //NOSONAR
            return null;
        }
    }

    /**
     * Same like {@link #parameter(String, String)}, but converts the
     * parameter to Integer if found.
     * <p/>
     * The parameter is decoded by default.
     *
     * @param name         The name of the post or query parameter
     * @param defaultValue A default value if parameter not found.
     * @return The value of the parameter of the defaultValue if not found.
     */
    @Override
    public Integer parameterAsInteger(String name, Integer defaultValue) {
        Integer parameter = parameterAsInteger(name);
        if (parameter == null) {
            return defaultValue;
        }
        return parameter;
    }

    @Override
    public Boolean parameterAsBoolean(String name) {
        String parameter = parameter(name);
        try {
            return Boolean.parseBoolean(parameter);
        } catch (Exception e) { //NOSONAR
            return null;
        }
    }

    @Override
    public Boolean parameterAsBoolean(String name, boolean defaultValue) {
        // We have to check if the map contains the key, as the retrieval method returns false on missing key.
        if (! parameters().containsKey(name)) {
            return defaultValue;
        }
        Boolean parameter = parameterAsBoolean(name);
        if (parameter == null) {
            return defaultValue;
        }
        return parameter;
    }

    /**
     * Get the path parameter for the given key.
     * <p/>
     * The parameter will be decoded based on the RFCs.
     * <p/>
     * Check out http://docs.oracle.com/javase/6/docs/api/java/net/URI.html for
     * more information.
     *
     * @param name The name of the path parameter in a route. Eg
     *             /{myName}/rest/of/url
     * @return The decoded path parameter, or null if no such path parameter was
     * found.
     */
    @Override
    public String parameterFromPath(String name) {
        String encodedParameter = route.getPathParametersEncoded(
                path()).get(name);

        if (encodedParameter == null) {
            return null;
        } else {
            return URI.create(encodedParameter).getPath();
        }
    }

    /**
     * Get the path parameter for the given key.
     * <p/>
     * Returns the raw path part. That means you can get stuff like:
     * blue%2Fred%3Fand+green
     *
     * @param name The name of the path parameter in a route. Eg
     *             /{myName}/rest/of/url
     * @return The encoded (!) path parameter, or null if no such path parameter
     * was found.
     */
    @Override
    public String parameterFromPathEncoded(String name) {
        return route.getPathParametersEncoded(path()).get(name);
    }

    /**
     * Get the path parameter for the given key and convert it to Integer.
     * <p/>
     * The parameter will be decoded based on the RFCs.
     * <p/>
     * Check out http://docs.oracle.com/javase/6/docs/api/java/net/URI.html for
     * more information.
     *
     * @param key the key of the path parameter
     * @return the numeric path parameter, or null of no such path parameter is
     * defined, or if it cannot be parsed to int
     */
    @Override
    public Integer parameterFromPathAsInteger(String key) {
        String parameter = parameterFromPath(key);
        if (parameter == null) {
            return null;
        } else {
            return Integer.parseInt(parameter);
        }
    }

    /**
     * Get all the parameters from the request.
     * This method does not check the attributes.
     *
     * @return The parameters
     */
    @Override
    public Map<String, List<String>> parameters() {
        return queryStringDecoder.parameters();
    }

    /**
     * Get the (first) request header with the given name.
     *
     * @return The header value
     */
    @Override
    public String header(String name) {
        return httpRequest.headers().get(name);
    }

    /**
     * Get all the request headers with the given name.
     *
     * @return the header values
     */
    @Override
    public List<String> headers(String name) {
        return httpRequest.headers().getAll(name);
    }

    /**
     * Get all the headers from the request.
     *
     * @return The headers
     */
    @Override
    public Map<String, List<String>> headers() {
        return request.headers();
    }

    /**
     * Get the cookie value from the request, if defined.
     *
     * @param name The name of the cookie
     * @return The cookie value, or null if the cookie was not found
     */
    @Override
    public String cookieValue(String name) {
        return CookieHelper.getCookieValue(name, request().cookies());
    }

    /**
     * This will give you the request body nicely parsed. You can register your
     * own parsers depending on the request type.
     * <p/>
     *
     * @param classOfT The class of the result.
     * @return The parsed request or null if something went wrong.
     */
    @Override
    public <T> T body(Class<T> classOfT) {
        String rawContentType = request().contentType();

        // If the Content-type: xxx header is not set we return null.
        // we cannot parse that request.
        if (rawContentType == null) {
            return null;
        }

        // If Content-type is application/json; charset=utf-8 we split away the charset
        // application/json
        String contentTypeOnly = getContentTypeFromContentTypeAndCharacterSetting(
                rawContentType);

        BodyParser parser = services.getContentEngines().getBodyParserEngineForContentType(contentTypeOnly);

        if (parser == null) {
            return null;
        }

        return parser.invoke(this, classOfT);
    }

    /**
     * Retrieves the request body as a String. If the request has no body, {@code null} is returned.
     *
     * @return the body as String
     */
    public String body() {
        return raw;
    }

    /**
     * Get the reader to read the request.
     *
     * @return The reader
     */
    @Override
    public BufferedReader getReader() throws IOException {
        if (raw != null) {
            return IOUtils.toBufferedReader(new StringReader(raw));
        }
        return null;
    }

    /**
     * Get the route for this context.
     *
     * @return The route
     */
    @Override
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        // Can be called only once, with a non null route.
        Preconditions.checkState(this.route == null);
        Preconditions.checkNotNull(route);
        this.route = route;
    }

    /**
     * Check if request is of type multipart. Important when you want to process
     * uploads for instance.
     * <p/>
     * Also check out: http://commons.apache.org/fileupload/streaming.html
     *
     * @return true if request is of type multipart.
     */
    @Override
    public boolean isMultipart() {
        return MimeTypes.MULTIPART.equals(request().contentType());
    }

    /**
     * Gets the collection of uploaded files.
     *
     * @return the collection of files, {@literal empty} if no files.
     */
    @Override
    public Collection<? extends FileItem> getFiles() {
        return files;
    }

    /**
     * Gets the uploaded file having a form's field matching the given name.
     *
     * @param name the name of the field of the form that have uploaded the file
     * @return the file object, {@literal null} if there are no file with this name
     */
    @Override
    public FileItem getFile(String name) {
        for (FileItem item : files) {
            if (item.field().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void cleanup() {
        for (FileItemFromNetty file : files) {
            file.upload().release();
        }
    }
}
