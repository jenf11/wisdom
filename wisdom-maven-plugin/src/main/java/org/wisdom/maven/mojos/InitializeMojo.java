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
package org.wisdom.maven.mojos;


import aQute.bnd.osgi.Analyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilder;
import org.codehaus.plexus.util.PropertyUtils;
import org.wisdom.maven.utils.BuildConstants;
import org.wisdom.maven.utils.DefaultMaven2OsgiConverter;
import org.wisdom.maven.utils.DependencyCopy;
import org.wisdom.maven.utils.WisdomRuntimeExpander;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Mojo preparing the Wisdom runtime.
 */
@Mojo(name = "initialize", threadSafe = false,
        requiresDependencyResolution = ResolutionScope.COMPILE,
        requiresProject = true,
        defaultPhase = LifecyclePhase.INITIALIZE)
public class InitializeMojo extends AbstractWisdomMojo {

    private static final String MAVEN_SYMBOLICNAME = "maven-symbolicname";
    private static final String OSGI_PROPERTIES = "target/osgi/osgi.properties";
    private static final String DEPENDENCIES = "target/osgi/dependencies.json";

    @Parameter(defaultValue = "false")
    private boolean excludeTransitive;

    @Parameter(defaultValue = "true")
    private boolean excludeTransitiveWebJars;

    /**
     * Deploy the test dependencies to run tests. This option should be used with caution as it may add to much
     * bundles to your runtime.
     */
    @Parameter(defaultValue = "false")
    private boolean deployTestDependencies;

    /**
     * The dependency graph builder to use.
     */
    @Component(hint = "default")
    private DependencyGraphBuilder dependencyGraphBuilder;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug("Wisdom Maven Plugin version: " + BuildConstants.get("WISDOM_PLUGIN_VERSION"));

        // Expand if needed.
        if (WisdomRuntimeExpander.expand(this, getWisdomRootDirectory())) {
            getLog().info("Wisdom Runtime installed in " + getWisdomRootDirectory().getAbsolutePath());
        }

        // Copy compile dependencies that are bundles to the application directory.
        try {
            DependencyCopy.copyBundles(this, dependencyGraphBuilder, !excludeTransitive, deployTestDependencies);
            DependencyCopy.extractWebJars(this, dependencyGraphBuilder, !excludeTransitiveWebJars);
        } catch (IOException e) {
            throw new MojoExecutionException("Cannot copy dependencies", e);
        }

        // Install node.
        try {
            node.installIfNotInstalled();
        } catch (IOException e) {
            getLog().error("Cannot install node and npm - asset processing won't work", e);
        }

        // Prepare OSGi packaging
        Properties properties = getDefaultProperties(this, project);
        try {
            write(properties);
        } catch (IOException e) {
            throw new MojoExecutionException("Cannot write the OSGi metadata to " + OSGI_PROPERTIES, e);
        }

        // Store dependencies as JSON
        try {
            dumpDependencies();
        } catch (IOException e) {
            throw new MojoExecutionException("Cannot write the dependency metadata to " + DEPENDENCIES, e);
        }
    }

    private void dumpDependencies() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = mapper.createArrayNode();
        for (Artifact artifact : project.getArtifacts()) {
            ObjectNode node = mapper.createObjectNode();
            node.put("groupId", artifact.getGroupId());
            node.put("artifactId", artifact.getArtifactId());
            node.put("version", artifact.getVersion());
            if (artifact.getClassifier() != null) {
                node.put("classifier", artifact.getClassifier());
            }
            node.put("scope", artifact.getScope());
            node.put("file", artifact.getFile().getAbsolutePath());
            array.add(node);
        }

        File dependencies = new File(project.getBasedir(), DEPENDENCIES);
        mapper.writerWithDefaultPrettyPrinter().writeValue(dependencies, array);

    }

    private void write(Properties properties) throws IOException {
        File file = new File(project.getBasedir(), OSGI_PROPERTIES);
        file.getParentFile().mkdirs();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            properties.store(fos, "");
        } finally {
            IOUtils.closeQuietly(fos);
        }

    }


    private static Properties getDefaultProperties(AbstractWisdomMojo mojo, MavenProject currentProject) {
        Properties properties = new Properties();
        String bsn = DefaultMaven2OsgiConverter.getBundleSymbolicName(currentProject.getArtifact());

        // Setup defaults
        properties.put(MAVEN_SYMBOLICNAME, bsn);
        properties.put(Analyzer.BUNDLE_SYMBOLICNAME, bsn);
        properties.put(Analyzer.IMPORT_PACKAGE, "*");
        properties.put(Analyzer.BUNDLE_VERSION, DefaultMaven2OsgiConverter.getVersion(currentProject.getVersion()));

        header(properties, Analyzer.BUNDLE_DESCRIPTION, currentProject.getDescription());
        StringBuilder licenseText = printLicenses(currentProject.getLicenses());
        if (licenseText != null) {
            header(properties, Analyzer.BUNDLE_LICENSE, licenseText);
        }
        header(properties, Analyzer.BUNDLE_NAME, currentProject.getName());

        if (currentProject.getOrganization() != null) {
            if (currentProject.getOrganization().getName() != null) {
                String organizationName = currentProject.getOrganization().getName();
                header(properties, Analyzer.BUNDLE_VENDOR, organizationName);
                properties.put("project.organization.name", organizationName);
                properties.put("pom.organization.name", organizationName);
            }
            if (currentProject.getOrganization().getUrl() != null) {
                String organizationUrl = currentProject.getOrganization().getUrl();
                header(properties, Analyzer.BUNDLE_DOCURL, organizationUrl);
                properties.put("project.organization.url", organizationUrl);
                properties.put("pom.organization.url", organizationUrl);
            }
        }

        properties.putAll(currentProject.getProperties());
        properties.putAll(currentProject.getModel().getProperties());

        for (String s : currentProject.getFilters()) {
            File filterFile = new File(s);
            if (filterFile.isFile()) {
                properties.putAll(PropertyUtils.loadProperties(filterFile));
            }
        }

        properties.putAll(getProperties(currentProject.getModel(), "project.build."));
        properties.putAll(getProperties(currentProject.getModel(), "pom."));
        properties.putAll(getProperties(currentProject.getModel(), "project."));

        properties.put("project.baseDir", mojo.basedir.getAbsolutePath());
        properties.put("project.build.directory", mojo.buildDirectory.getAbsolutePath());
        properties.put("project.build.outputdirectory", new File(mojo.buildDirectory, "classes").getAbsolutePath());

        return properties;
    }

    private static Map getProperties(Model projectModel, String prefix) {
        Map<String, String> properties = new LinkedHashMap<>();
        Method methods[] = Model.class.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get")) {
                try {
                    Object v = method.invoke(projectModel);
                    if (v != null) {
                        name = prefix + Character.toLowerCase(name.charAt(3)) + name.substring(4);
                        if (v.getClass().isArray()) {
                            properties.put(name, Arrays.asList((Object[]) v).toString());
                        } else {
                            properties.put(name, v.toString());
                        }
                    }
                } catch (Exception e) {  //NOSONAR
                    // too bad
                }
            }
        }
        return properties;
    }

    private static StringBuilder printLicenses(List licenses) {
        if (licenses == null || licenses.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String del = "";
        for (Object license : licenses) {
            License l = (License) license;
            String url = l.getUrl();
            if (url == null) {
                continue;
            }
            sb.append(del);
            sb.append(url);
            del = ", ";
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb;
    }

    private static void header(Properties properties, String key, Object value) {
        if (value == null) {
            return;
        }

        if (value instanceof Collection && ((Collection) value).isEmpty()) {
            return;
        }

        properties.put(key, value.toString().replaceAll("[\r\n]", ""));
    }

}
