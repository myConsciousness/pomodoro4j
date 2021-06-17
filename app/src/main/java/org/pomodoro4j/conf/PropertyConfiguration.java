/*
 * Copyright 2021 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.pomodoro4j.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class PropertyConfiguration extends ConfigurationBase {

    /**
     * The pomodoro4j properties
     */
    private static final String POMODORO4J_PROPERTIES = "pomodoro4j.properties";

    /**
     * The prefix of property
     */
    private static final String PROPERTY_PREFIX = "pomodoro4j.";

    /**
     * {@code "minutes.concentration"}
     */
    private static final String CONCENTRATION_MINUTES = "minutes.concentration";

    /**
     * {@code "minutes.break"}
     */
    private static final String BREAK_MINUTES = "minutes.break";

    /**
     * {@code "minutes.longerBreak"}
     */
    private static final String LONGER_BREAK_MINUTES = "minutes.longerBreak";

    /**
     * {@code "count.longerBreak"}
     */
    private static final String COUNT_UNTIL_LONGER_BREAK = "count.longerBreak";

    /**
     * The default value of {@code "minutes.concentration"} property
     */
    private static final int DEFAULT_CONCENTRATION_MINUTES = 25;

    /**
     * The default value of {@code "minutes.break"} property
     */
    private static final int DEFAULT_BREAK_MINUTES = 5;

    /**
     * The default value of {@code "minutes.longerBreak"} property
     */
    private static final int DEFAULT_LONGER_BREAK_MINUTES = 20;

    /**
     * The default value of {@code "count.longerBreak"} property
     */
    private static final int DEFAULT_COUNT_LONGER_BREAK = 4;

    public PropertyConfiguration() {
        this("/");
    }

    public PropertyConfiguration(final InputStream inputStream) {
        super();

        final Properties properties = new Properties();
        this.loadProperties(properties, inputStream);
        this.setFieldsWithTreePath(properties, "/");
    }

    public PropertyConfiguration(@NonNull final Properties properties) {
        this(properties, "/");
    }

    public PropertyConfiguration(@NonNull final Properties properties, @NonNull final String treePath) {
        super();
        this.setFieldsWithTreePath(properties, treePath);
    }

    /**
     * The constructor.
     *
     * @param treePath The tree path
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    protected PropertyConfiguration(@NonNull final String treePath) {
        super();

        Properties properties;

        try {
            properties = (Properties) System.getProperties().clone();
            try {
                final Map<String, String> environments = System.getenv();
                for (final String key : environments.keySet()) {
                    properties.setProperty(key, environments.get(key));
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }

            this.normalizeProperties(properties);
        } catch (SecurityException e) {
            // Unsigned applets are not allowed to access System properties
            e.printStackTrace();
            properties = new Properties();
        }

        // override System properties with ./pomodoro4j.properties in the classpath
        this.loadProperties(properties, "." + File.separatorChar + POMODORO4J_PROPERTIES);
        // then, override with /pomodoro4j.properties in the classpath
        this.loadProperties(properties, Configuration.class.getResourceAsStream("/" + POMODORO4J_PROPERTIES));
        // then, override with /WEB/INF/pomodoro4j.properties in the classpath
        this.loadProperties(properties, Configuration.class.getResourceAsStream("/WEB-INF/" + POMODORO4J_PROPERTIES));

        try {
            // for Google App Engine
            this.loadProperties(properties, new FileInputStream("WEB-INF/" + POMODORO4J_PROPERTIES));
        } catch (SecurityException | FileNotFoundException e) {
            // When there is no property file
        }

        this.setFieldsWithTreePath(properties, treePath);
    }

    private boolean loadProperties(@NonNull final Properties properties, @NonNull final String path) {

        final File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return false;
        }

        try (final FileInputStream fileInputStream = new FileInputStream(file);) {
            properties.load(fileInputStream);
            this.normalizeProperties(properties);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean loadProperties(@NonNull final Properties properties, final InputStream inputStream) {
        try {
            properties.load(inputStream);
            this.normalizeProperties(properties);
            return true;
        } catch (Exception e) {
            // When there is no property file
            return false;
        }
    }

    private void normalizeProperties(@NonNull final Properties properties) {

        for (final Object keyObject : properties.keySet()) {
            final String key = (String) keyObject;

            if (key.contains(PROPERTY_PREFIX)) {
                properties.setProperty(this.getNormalizedKey(key, key.indexOf(PROPERTY_PREFIX)),
                        properties.getProperty(key));
            }
        }
    }

    private String getNormalizedKey(@NonNull final String key, final int prefixIndex) {
        return key.substring(0, prefixIndex) + key.substring(prefixIndex + PROPERTY_PREFIX.length());
    }

    /**
     * passing "/foo/bar" as treePath will result:<br>
     * 1. load [twitter4j.]restBaseURL<br>
     * 2. override the value with foo.[twitter4j.]restBaseURL<br>
     * 3. override the value with foo.bar.[twitter4j.]restBaseURL<br>
     *
     * @param props    properties to be loaded
     * @param treePath the path
     */
    private void setFieldsWithTreePath(@NonNull final Properties properties, @NonNull final String treePath) {

        this.setFieldsWithPrefix(properties, "");

        String prefix = null;
        for (final String string : treePath.split("/")) {
            if (!StringUtils.EMPTY.equals(string)) {
                this.setFieldsWithPrefix(properties,
                        prefix == null ? (prefix = string + ".") : (prefix += string + "."));
            }
        }
    }

    private void setFieldsWithPrefix(@NonNull final Properties properties, @NonNull final String prefix) {
        super.setConcentrationMinutes(
                this.getIntPropertyOrDefault(properties, prefix, CONCENTRATION_MINUTES, DEFAULT_CONCENTRATION_MINUTES));
        super.setBreakMinutes(this.getIntPropertyOrDefault(properties, prefix, BREAK_MINUTES, DEFAULT_BREAK_MINUTES));
        super.setLongerBreakMinutes(
                this.getIntPropertyOrDefault(properties, prefix, LONGER_BREAK_MINUTES, DEFAULT_LONGER_BREAK_MINUTES));
        super.setCountUntilLongerBreak(
                this.getIntPropertyOrDefault(properties, prefix, COUNT_UNTIL_LONGER_BREAK, DEFAULT_COUNT_LONGER_BREAK));
    }

    private int getIntPropertyOrDefault(@NonNull final Properties properties, @NonNull final String prefix,
            @NonNull final String name, final int defaultValue) {

        if (!this.isPropertyExist(properties, prefix, CONCENTRATION_MINUTES)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(properties.getProperty(prefix + name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private boolean isPropertyExist(@NonNull final Properties properties, @NonNull final String prefix,
            @NonNull final String name) {
        return !StringUtils.isEmpty(properties.getProperty(prefix + name));
    }
}
