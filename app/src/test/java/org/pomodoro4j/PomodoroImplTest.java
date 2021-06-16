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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.pomodoro4j.conf.ConfigurationBuilder;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class PomodoroImplTest {

    @Nested
    class TestStart {

        @Test
        void testStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
        }

        @Test
        void testPomodoroStateAfterStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
        }

        @Test
        void testPomodoroStateBeforeStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertEquals(PomodoroState.INITIALIZED, sut.getPomodoroState());
        }
    }

    @Nested
    class TestIsOngoing {

        @Test
        void testAfterStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertTrue(sut.isOngoing());
        }

        @Test
        void testBeforeStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertFalse(sut.isOngoing());
        }
    }

    @Nested
    class TestShouldStartBreak {

        @Test
        void testWhenShouldStartBreak() throws InterruptedException {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setConcentrationMinutes(1).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());

            TimeUnit.MINUTES.sleep(1);
            assertTrue(sut.shouldStartBreak());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
        }

        @Test
        void testWhenShouldNotStartBreak() throws InterruptedException {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setConcentrationMinutes(1).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertFalse(sut.shouldStartBreak());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
        }

        @Test
        void testWhenNotStarted() throws InterruptedException {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setConcentrationMinutes(1).build()));

            assertNotNull(sut);

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.shouldStartBreak());
            assertNotNull(actual);
            assertEquals(PomodoroState.INITIALIZED, sut.getPomodoroState());
        }
    }

    @Nested
    class TestIsBreakOngoing {

        @Test
        void testIsBreakOngoing() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.BREAKING, sut.getPomodoroState());

            final boolean actual = assertDoesNotThrow(() -> sut.isBreakOngoing());
            assertEquals(true, actual);
        }

        @Test
        void testWhenBreakIsNotOngoing() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.BREAKING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.endBreak());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());

            final boolean actual = assertDoesNotThrow(() -> sut.isBreakOngoing());
            assertEquals(false, actual);
        }

        @Test
        void testIsBreakOngoingBeforeStartBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.isBreakOngoing());
            assertNotNull(actual);
        }

        @Test
        void testWhenPomodoroIsFinished() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setCountUntilLongerBreak(0).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.LONGER_BREAKING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.endBreak());
            assertEquals(PomodoroState.FINISHED, sut.getPomodoroState());

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.isBreakOngoing());
            assertNotNull(actual);
        }
    }

    @Nested
    class TestStartBreak {

        @Test
        void testStartBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.BREAKING, sut.getPomodoroState());
        }

        @Test
        void testStartBreakBeforeStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.startBreak());
            assertNotNull(actual);
            assertEquals(PomodoroState.INITIALIZED, sut.getPomodoroState());
        }

        @Test
        void testStartLongerBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setCountUntilLongerBreak(1).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.BREAKING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.endBreak());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.LONGER_BREAKING, sut.getPomodoroState());
        }
    }

    @Nested
    class TestEndBreak {

        @Test
        void testEndBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setCountUntilLongerBreak(1).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.BREAKING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.endBreak());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
        }

        @Test
        void testEndBreakBeforeStart() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.endBreak());
            assertNotNull(actual);
            assertEquals(PomodoroState.INITIALIZED, sut.getPomodoroState());
        }

        @Test
        void testEndBreakBeforeStartBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());

            final PomodoroException actual = assertThrows(PomodoroException.class, () -> sut.endBreak());
            assertNotNull(actual);
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
        }

        @Test
        void testEndLongerBreak() {
            final PomodoroImpl sut = assertDoesNotThrow(
                    () -> new PomodoroImpl(ConfigurationBuilder.newBuilder().setCountUntilLongerBreak(0).build()));

            assertNotNull(sut);
            assertDoesNotThrow(() -> sut.start());
            assertEquals(PomodoroState.CONCENTRATING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.startBreak());
            assertEquals(PomodoroState.LONGER_BREAKING, sut.getPomodoroState());
            assertDoesNotThrow(() -> sut.endBreak());
            assertEquals(PomodoroState.FINISHED, sut.getPomodoroState());
        }
    }
}
