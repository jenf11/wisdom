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
package org.wisdom.api.router;

import org.wisdom.api.Controller;
import org.wisdom.api.http.HttpMethod;

import java.util.Collection;
import java.util.Map;

/**
 * The router service interface.
 */
public interface Router {

    /**
     * Gets the route for the given method and uri.
     *
     * @param method the method
     * @param uri    the uri
     * @return the route, {@literal unbound} if no controller handles the request
     */
    Route getRouteFor(HttpMethod method, String uri);

    /**
     * Gets the route for the given method and uri.
     *
     * @param method the method (must be a valid HTTP method)
     * @param uri    the uri
     * @return the route, {@literal unbound} if no controller handles the request
     */
    Route getRouteFor(String method, String uri);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param params map of parameter name - value
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, Map<String,
            Object> params);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param className the controller class
     * @param method    the controller method
     * @param params    map of parameter name - value
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(String className, String method, Map<String, Object> params);

    /**
     * Gets the url of the route handled by the specified action method. The action does not takes parameters.
     *
     * @param className the controller class
     * @param method    the controller method
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(String className, String method);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param params     map of parameter name - value
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(Controller controller, String method, Map<String,
            Object> params);

    /**
     * Gets the url of the route handled by the specified action method. The action does not takes parameters.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method);

    /**
     * Gets the url of the route handled by the specified action method. The action does not takes parameters.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @return the url, {@literal null} if the action method is not found
     */
    String getReverseRouteFor(Controller controller, String method);

    /**
     * Gets the set of routes that are currently handled by controllers.
     *
     * @return the set of routes
     */
    Collection<Route> getRoutes();

    // Method avoiding using maps in controllers.

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param var1       the first parameter name
     * @param val1       the first parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Controller controller, String method, String var1, Object val1);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param var1       the first parameter name
     * @param val1       the first parameter value
     * @param var2       the second parameter name
     * @param val2       the second parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Controller controller, String method, String var1, Object val1, String var2,
                              Object val2);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param var1       the first parameter name
     * @param val1       the first parameter value
     * @param var2       the second parameter name
     * @param val2       the second parameter value
     * @param var3       the third parameter name
     * @param val3       the third parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Controller controller, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param var1       the first parameter name
     * @param val1       the first parameter value
     * @param var2       the second parameter name
     * @param val2       the second parameter value
     * @param var3       the third parameter name
     * @param val3       the third parameter value
     * @param var4       the fourth parameter name
     * @param val4       the fourth parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Controller controller, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3, String var4, Object val4);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param controller the controller object
     * @param method     the controller method
     * @param var1       the first parameter name
     * @param val1       the first parameter value
     * @param var2       the second parameter name
     * @param val2       the second parameter value
     * @param var3       the third parameter name
     * @param val3       the third parameter value
     * @param var4       the fourth parameter name
     * @param val4       the fourth parameter value
     * @param var5       the fifth parameter name
     * @param val5       the fifth parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Controller controller, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3, String var4, Object val4, String var5,
                              Object val5);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param var1   the first parameter name
     * @param val1   the first parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, String var1, Object val1);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param var1   the first parameter name
     * @param val1   the first parameter value
     * @param var2   the second parameter name
     * @param val2   the second parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, String var1, Object val1, String var2,
                              Object val2);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param var1   the first parameter name
     * @param val1   the first parameter value
     * @param var2   the second parameter name
     * @param val2   the second parameter value
     * @param var3   the third parameter name
     * @param val3   the third parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param var1   the first parameter name
     * @param val1   the first parameter value
     * @param var2   the second parameter name
     * @param val2   the second parameter value
     * @param var3   the third parameter name
     * @param val3   the third parameter value
     * @param var4   the fourth parameter name
     * @param val4   the fourth parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3, String var4, Object val4);

    /**
     * Gets the url of the route handled by the specified action method.
     *
     * @param clazz  the controller class
     * @param method the controller method
     * @param var1   the first parameter name
     * @param val1   the first parameter value
     * @param var2   the second parameter name
     * @param val2   the second parameter value
     * @param var3   the third parameter name
     * @param val3   the third parameter value
     * @param var4   the fourth parameter name
     * @param val4   the fourth parameter value
     * @param var5   the fifth parameter name
     * @param val5   the fifth parameter value
     * @return the url, {@literal null} if the action method is not found.
     */
    String getReverseRouteFor(Class<? extends Controller> clazz, String method, String var1, Object val1, String var2,
                              Object val2, String var3, Object val3, String var4, Object val4, String var5,
                              Object val5);
}
