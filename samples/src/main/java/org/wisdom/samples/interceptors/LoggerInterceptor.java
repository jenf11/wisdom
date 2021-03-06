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
package org.wisdom.samples.interceptors;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.interception.RequestContext;
import org.wisdom.api.interception.Interceptor;
import org.wisdom.api.http.Result;

/**
 *
 */
@Component
@Provides(specifications = Interceptor.class)
@Instantiate
public class LoggerInterceptor extends Interceptor<Logged> {

    Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public Result call(Logged configuration, RequestContext context) throws Throwable {
        logger.info("Invoking " + context.context().request().method() + " " + context.context().request().uri());
        long begin = System.currentTimeMillis();
        Result r = context.proceed();
        long end = System.currentTimeMillis();
        if (configuration.duration()) {
            logger.info("Result computed in " + (end - begin) + " ms");
        }
        return r;
    }

    @Override
    public Class annotation() {
        return Logged.class;
    }
}
