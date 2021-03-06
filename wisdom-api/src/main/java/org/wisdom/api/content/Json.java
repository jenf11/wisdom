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
package org.wisdom.api.content;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.InputStream;

/**
 * A service interface used to handle Json objects and String.
 * It is basically a layer on top of Jackson. Be aware that implementation should support the dynamic addition and
 * removal of serializer/deserializer, making this service the main entry point to the Json support.
 */
public interface Json {

    /**
     * Gets the current mapper.
     * @return the mapper
     */
    public ObjectMapper mapper();

    /**
     * Maps the given object to a JsonNode.
     * In addition to the default Jackson transformation, serializer dynamically added to the Json support are used.
     * @param data the data to transform to json
     * @return the resulting json node
     */
    public JsonNode toJson(final Object data);

    /**
     * Builds a new instance of the given class <em>clazz</em> from the given Json object.
     * @param json the json node
     * @param clazz the class of the instance to construct
     * @return an instance of the class.
     */
    public <A> A fromJson(JsonNode json, Class<A> clazz);

    /**
     * Builds a new instance of the given class <em>clazz</em> from the given Json string.
     * @param json the json string
     * @param clazz the class of the instance to construct
     * @return an instance of the class.
     */
    public <A> A fromJson(String json, Class<A> clazz);

    /**
     *
     * @param json
     * @return
     */
    public String stringify(JsonNode json);

    public JsonNode parse(String src);

    public JsonNode parse(InputStream stream);

    public ObjectNode newObject();

    public ArrayNode newArray();

}
