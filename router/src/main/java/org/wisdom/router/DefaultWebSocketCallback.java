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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.Parameter;
import org.wisdom.api.router.RouteUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common logic shared by all callbacks.
 */
public class DefaultWebSocketCallback {

    private final Controller controller;
    private final Method method;
    private final Pattern regex;
    private final ImmutableList<String> parameterNames;
    protected List<RouteUtils.Argument> arguments;

    public DefaultWebSocketCallback(Controller controller, Method method, String uri) {
        this.controller = controller;
        this.method = method;
        this.regex = Pattern.compile(RouteUtils.convertRawUriToRegex(uri));
        this.parameterNames = ImmutableList.copyOf(RouteUtils.extractParameters(uri));
    }

    public Controller getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getRegex() {
        return regex;
    }

    public List<RouteUtils.Argument> buildArguments(Method method) {
        List<RouteUtils.Argument> args = new ArrayList<>();
        Annotation[][] annotations = method.getParameterAnnotations();
        Class<?>[] typesOfParameters = method.getParameterTypes();
        for (int i = 0; i < annotations.length; i++) {
            boolean sourceDetected = false;
            for (int j = 0; !sourceDetected && j < annotations[i].length; j++) {
                Annotation annotation = annotations[i][j];
                if (annotation instanceof Parameter) {
                    Parameter parameter = (Parameter) annotation;
                    args.add(new RouteUtils.Argument(parameter.value(),
                            RouteUtils.Source.PARAMETER, typesOfParameters[i]));
                    sourceDetected = true;
                }
            }
            if (!sourceDetected) {
                // All parameters must have been annotated.
                WebSocketRouter.getLogger().error("The method {} has a parameter without annotations indicating " +
                        " the injected data. Only @Parameter annotations are supported in web sockets callbacks.",
                        method.getName());
                return Collections.emptyList();
            }
        }
        return args;
    }

    public boolean matches(String url) {
        return regex.matcher(url).matches();
    }

    public boolean check() {
        if (!method.getReturnType().equals(Void.TYPE)) {
            WebSocketRouter.getLogger().error("The method {} annotated with a web socket callback is not well-formed. " +
                    "These methods receive only parameter annotated with @Parameter and do not return anything",
                    method.getName());
            return false;
        }

        List<RouteUtils.Argument> localArguments = buildArguments(method);
        if (localArguments == null) {
            return false;
        } else {
            this.arguments = localArguments;
            return true;
        }
    }

    public Map<String, String> getPathParametersEncoded(String uri) {
        Map<String, String> map = Maps.newHashMap();
        Matcher m = regex.matcher(uri);
        if (m.matches()) {
            for (int i = 1; i < m.groupCount() + 1; i++) {
                map.put(parameterNames.get(i - 1), m.group(i));
            }
        }
        return map;
    }

    public void invoke(String uri, String client) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> values = getPathParametersEncoded(uri);
        Object[] parameters = new Object[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            RouteUtils.Argument argument = arguments.get(i);
            if (argument.getName().equals("client")  && argument.getType().equals(String.class)) {
                parameters[i] = client;
            } else {
                parameters[i] = RouteUtils.getParameter(argument, values);
            }
        }
        method.invoke(controller, parameters);
    }
}
