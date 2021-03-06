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
package org.wisdom.asciidoc;

import org.asciidoctor.AbstractDirectoryWalker;

import java.io.File;
import java.util.List;

/**
 * A directory walker supporting several extensions.
 */
public class CustomExtensionDirectoryWalker extends AbstractDirectoryWalker {
    private final List<String> extensions;

    public CustomExtensionDirectoryWalker(final String root, final List<String> extensions) {
        super(root);
        this.extensions = extensions;
    }

    @Override
    protected boolean isAcceptedFile(final File filename) {
        final String name = filename.getName();
        for (final String extension : extensions) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
