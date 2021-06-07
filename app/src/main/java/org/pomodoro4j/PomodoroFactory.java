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

import org.pomodoro4j.conf.Configuration;
import org.pomodoro4j.conf.ConfigurationContext;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class PomodoroFactory implements Serializable {

    private static final Constructor<Pomodoro> POMODORO_CONSTRUCTOR;
    private final Configuration configuration;

    static {

        Constructor<Pomodoro> constructor;
        Class<Pomodoro> clazz;

        try {
            clazz = (Class<Pomodoro>) Class.forName("org.pomodoro4j.PomodoroImpl");
            constructor = clazz.getDeclaredConstructor(Configuration.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }

        POMODORO_CONSTRUCTOR = constructor;
    }

    public PomodoroFactory() {
        this(ConfigurationContext.getInstance());
    }

    public PomodoroFactory(@NonNull final Configuration configuration) {
        this.configuration = configuration;
    }
}
