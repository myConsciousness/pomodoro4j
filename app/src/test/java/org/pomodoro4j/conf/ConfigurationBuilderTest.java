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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class ConfigurationBuilderTest {

    @Nested
    class TestSetters {

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testSetConcentrationMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder actual = assertDoesNotThrow(() -> sut.setConcentrationMinutes(parameter));
            assertNotNull(actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testSetBreakMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder actual = assertDoesNotThrow(() -> sut.setBreakMinutes(parameter));
            assertNotNull(actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testSetLongerBreakMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder actual = assertDoesNotThrow(() -> sut.setLongerBreakMinutes(parameter));
            assertNotNull(actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testSetCountUntilLongerBreak(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder actual = assertDoesNotThrow(() -> sut.setCountUntilLongerBreak(parameter));
            assertNotNull(actual);
        }
    }

    @Nested
    class TestGetters {

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testGetConcentrationMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder configurationBuilder = assertDoesNotThrow(
                    () -> sut.setConcentrationMinutes(parameter));
            final int actual = assertDoesNotThrow(() -> configurationBuilder.build().getConcentrationMinutes());
            assertEquals(parameter, actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testGetBreakMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder configurationBuilder = assertDoesNotThrow(() -> sut.setBreakMinutes(parameter));
            final int actual = assertDoesNotThrow(() -> configurationBuilder.build().getBreakMinutes());
            assertEquals(parameter, actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testGetLongerBreakMinutes(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder configurationBuilder = assertDoesNotThrow(
                    () -> sut.setLongerBreakMinutes(parameter));
            final int actual = assertDoesNotThrow(() -> configurationBuilder.build().getLongerBreakMinutes());
            assertEquals(parameter, actual);
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -1, 0, 1, 100 })
        void testGetCountUntilLongerBreak(final int parameter) {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            final ConfigurationBuilder configurationBuilder = assertDoesNotThrow(
                    () -> sut.setCountUntilLongerBreak(parameter));
            final int actual = assertDoesNotThrow(() -> configurationBuilder.build().getCountUntilLongerBreak());
            assertEquals(parameter, actual);
        }
    }

    @Nested
    class TestBuild {

        @Test
        void testWhenAlreadyBuilt() {
            final ConfigurationBuilder sut = ConfigurationBuilder.newBuilder();
            assertDoesNotThrow(() -> sut.build());
            final IllegalStateException actual = assertThrows(IllegalStateException.class, () -> sut.build());

            assertNotNull(actual);
            assertEquals("Cannot use this builder any longer, build() has already been called.", actual.getMessage());
        }
    }
}
