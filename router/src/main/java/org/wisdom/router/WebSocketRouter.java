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
package org.wisdom.router;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.apache.felix.ipojo.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.Closed;
import org.wisdom.api.annotations.OnMessage;
import org.wisdom.api.annotations.Opened;
import org.wisdom.api.content.ContentEngine;
import org.wisdom.api.http.websockets.Publisher;
import org.wisdom.api.http.websockets.WebSocketDispatcher;
import org.wisdom.api.http.websockets.WebSocketListener;
import org.wisdom.api.router.RouteUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Component handling web socket frame routing.
 */
@Component(immediate = true)
@Provides(specifications = Publisher.class)
@Instantiate(name = "WebSocketRouter")
public class WebSocketRouter implements WebSocketListener, Publisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketRouter.class);
    
    @Requires
    private WebSocketDispatcher dispatcher;
    private List<DefaultWebSocketCallback> opens = new ArrayList<>();
    private List<DefaultWebSocketCallback> closes = new ArrayList<>();
    private List<OnMessageWebSocketCallback> listeners = new ArrayList<>();

    @Requires(optional = true)
    private ContentEngine engine;
    

    public static Logger getLogger() {
        return LOGGER;
    }

    @Validate
    public void start() {
        dispatcher.register(this);
    }

    @Invalidate
    public void stop() {
        dispatcher.unregister(this);
    }

    @Bind(aggregate = true)
    public synchronized void bindController(Controller controller) {
        analyze(controller);
    }

    /**
     * Extracts all the annotation from the controller's method
     *
     * @param controller the controller to analyze
     */
    private void analyze(Controller controller) {
        String prefix = RouteUtils.getPath(controller);
        Method[] methods = controller.getClass().getMethods();
        for (Method method : methods) {
            Opened open = method.getAnnotation(Opened.class);
            Closed close = method.getAnnotation(Closed.class);
            OnMessage on = method.getAnnotation(OnMessage.class);
            if (open != null) {

                DefaultWebSocketCallback callback = new DefaultWebSocketCallback(controller, method,
                        RouteUtils.getPrefixedUri(prefix, open.value()));
                if (callback.check()) {
                    opens.add(callback);
                }
            }
            if (close != null) {
                DefaultWebSocketCallback callback = new DefaultWebSocketCallback(controller, method,
                        RouteUtils.getPrefixedUri(prefix, close.value()));
                if (callback.check()) {
                    closes.add(callback);
                }
            }
            if (on != null) {
                OnMessageWebSocketCallback callback = new OnMessageWebSocketCallback(controller, method,
                        RouteUtils.getPrefixedUri(prefix, on.value()));
                if (callback.check()) {
                    listeners.add(callback);
                }
            }
        }

    }

    @Unbind
    public synchronized void unbindController(Controller controller) {
        List<DefaultWebSocketCallback> toRemove = new ArrayList<>();
        for (DefaultWebSocketCallback open : opens) {
            if (open.getController() == controller) {
                toRemove.add(open);
            }
        }
        opens.removeAll(toRemove);

        toRemove.clear();
        for (DefaultWebSocketCallback close : closes) {
            if (close.getController() == controller) {
                toRemove.add(close);
            }
        }
        closes.removeAll(toRemove);

        toRemove.clear();
        for (DefaultWebSocketCallback callback : listeners) {
            if (callback.getController() == controller) {
                toRemove.add(callback);
            }
        }
        listeners.removeAll(toRemove);
    }

    @Override
    public void received(String uri, String from, byte[] content) {
        for (OnMessageWebSocketCallback listener : listeners) {
            if (listener.matches(uri)) {
                try {
                    listener.invoke(uri, from, content, engine);
                } catch (InvocationTargetException e) { //NOSONAR
                    LOGGER.error("An error occurred in the @OnMessage callback {}#{} : {}",
                            listener.getController().getClass().getName(), listener.getMethod().getName
                            (), e.getTargetException().getMessage(), e.getTargetException());
                } catch (Exception e) {
                    LOGGER.error("An error occurred in the @OnMessage callback {}#{} : {}",
                            listener.getController().getClass().getName(), listener.getMethod().getName(), e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void opened(String uri, String client) {
        for (DefaultWebSocketCallback open : opens) {
            if (open.matches(uri)) {
                try {
                    open.invoke(uri, client);
                } catch (InvocationTargetException e) { //NOSONAR
                    LOGGER.error("An error occurred in the @Open callback {}#{} : {}",
                            open.getController().getClass().getName(), open.getMethod().getName
                            (), e.getTargetException().getMessage(), e.getTargetException());
                } catch (Exception e) {
                    LOGGER.error("An error occurred in the @Open callback {}#{} : {}",
                            open.getController().getClass().getName(), open.getMethod().getName(), e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void closed(String uri, String client) {
        for (DefaultWebSocketCallback close : closes) {
            if (close.matches(uri)) {
                try {
                    close.invoke(uri, client);
                } catch (InvocationTargetException e) { //NOSONAR
                    LOGGER.error("An error occurred in the @Close callback {}#{} : {}",
                            close.getController().getClass().getName(), close.getMethod().getName
                            (), e.getTargetException().getMessage(), e.getTargetException());
                } catch (Exception e) {
                    LOGGER.error("An error occurred in the @Close callback {}#{} : {}",
                            close.getController().getClass().getName(), close.getMethod().getName(), e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void publish(String uri, String message) {
        dispatcher.publish(uri, message);
    }

    @Override
    public void publish(String uri, byte[] message) {
        dispatcher.publish(uri, message);
    }

    @Override
    public void publish(String uri, JsonNode message) {
        if (message == null) {
            dispatcher.publish(uri, NullNode.getInstance().asText());
        } else {
            dispatcher.publish(uri, message.asText());
        }
    }

    @Override
    public void send(String uri, String client, String message) {
        dispatcher.send(uri, client, message);
    }

    @Override
    public void send(String uri, String client, JsonNode message) {
        if (message == null) {
            dispatcher.send(uri, client, NullNode.getInstance().asText());
        } else {
            dispatcher.send(uri, client, message.asText());
        }
    }

    @Override
    public void send(String uri, String client, byte[] message) {
        dispatcher.send(uri, client, message);
    }
}
