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


import org.wisdom.api.http.Context;

public interface BodyParser {

    /**
     * Invoke the parser and get back a Java object populated
     * with the content of this request.
     * 
     * MUST BE THREAD SAFE TO CALL!
     * 
     * @param context The context
     * @param classOfT The class we expect
     * @return The object instance populated with all values from raw request
     */
    <T> T invoke(Context context, Class<T> classOfT);

    /**
     * Invoke the parser and get back a Java object populated
     * with the content of this request.
     *
     * MUST BE THREAD SAFE TO CALL!
     *
     * @param bytes the content
     * @param classOfT The class we expect
     * @return The object instance populated with all values from raw request
     */
    <T> T invoke(byte[] bytes, Class<T> classOfT);
    
    /**
     * The content type this BodyParserEngine can handle
     * 
     * MUST BE THREAD SAFE TO CALL!
     * 
     * @return the content type. this parser can handle - eg. "application/json"
     */
    String getContentType();

}
