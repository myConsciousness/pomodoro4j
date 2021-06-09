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

package org.pomodoro4j;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.pomodoro4j.conf.Configuration;
import org.pomodoro4j.conf.ConfigurationContext;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
public final class PomodoroFactory implements Serializable {

    /**
     * The package of pomodoro implementation
     */
    private static final String PACKAGE_POMODORO_IMPLEMENTATION = "org.pomodoro4j.PomodoroImpl";

    /**
     * The constructor of pomodoro
     */
    private static final Constructor<Pomodoro> POMODORO_CONSTRUCTOR = getPomodoroConstructor();

    /**
     * The configuration
     */
    private final Configuration configuration;

    /**
     * The default constructor.
     */
    private PomodoroFactory() {
        this(ConfigurationContext.getInstance());
    }

    /**
     * The constructor.
     *
     * @param configuration The configuration
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    private PomodoroFactory(@NonNull final Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns the new instance of {@link PomodoroFactory} .
     *
     * @return The new insrance of {@link PomodoroFactory}
     */
    public static PomodoroFactory newInstance() {
        return new PomodoroFactory();
    }

    /**
     * Returns the new instance of {@link PomodoroFactory} based on the
     * configuration passed as an argument.
     *
     * @param configuration The configuration
     * @return The new instance of {@link PomodoroFactory} based on the
     *         configuration
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    public static PomodoroFactory newInstance(@NonNull final Configuration configuration) {
        return new PomodoroFactory(configuration);
    }

    public Pomodoro getInstance() {
        try {
            return POMODORO_CONSTRUCTOR.newInstance(this.configuration);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns the constructor of pomodoro implementation.
     *
     * @return The constructor of pomodoro implementation
     */
    @SuppressWarnings("unchecked")
    private static Constructor<Pomodoro> getPomodoroConstructor() {
        try {
            return ((Class<Pomodoro>) Class.forName(PACKAGE_POMODORO_IMPLEMENTATION))
                    .getDeclaredConstructor(Configuration.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
