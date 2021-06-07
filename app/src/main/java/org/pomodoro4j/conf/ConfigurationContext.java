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

import java.lang.reflect.InvocationTargetException;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class ConfigurationContext {

    private static final String DEFAULT_CONFIGURATION_FACTORY = "pomodoro4j.conf.PropertyConfigurationFactory";
    private static final String CONFIGURATION_IMPLEMENTATION = "pomodoro4j.configurationFactory";
    private static final ConfigurationFactory CONFIGURATION_FACTORY;

    static {
        try {
            CONFIGURATION_FACTORY = (ConfigurationFactory) Class.forName(getConfigurationImpl())
                    .getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getConfigurationImpl() {
        try {
            return System.getProperty(CONFIGURATION_IMPLEMENTATION, DEFAULT_CONFIGURATION_FACTORY);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
            return DEFAULT_CONFIGURATION_FACTORY;
        }
    }

    public static Configuration getInstance() {
        return CONFIGURATION_FACTORY.getInstance();
    }

    public static Configuration getInstance(@NonNull final String configTreePath) {
        return CONFIGURATION_FACTORY.getInstance(configTreePath);
    }
}
