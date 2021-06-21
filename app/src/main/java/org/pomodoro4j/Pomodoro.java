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

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Pomodoro extends BreakSupport, Serializable {

    /**
     * Starts a pomodoro set and returns {@code true} for as long as this started
     * pomodoro set is ongoing.
     *
     * @return {@code true} if the Pomodoro set is ongoing, otherwise {@code false}
     */
    public boolean performs();

    /**
     * Stops the pomodoro set.
     */
    public void stop();

    /**
     * Resets the pomodoro set.
     */
    public void reset();

    /**
     * Returns the time this pomodoro was started in milliseconds, between the
     * current time and midnight, January 1, 1970 UTC.
     *
     * @return The time this pomodoro was started in milliseconds, between the
     *         current time and midnight, January 1, 1970 UTC
     */
    public long getStartMilliseconds();

    /**
     * Returns the time this pomodoro was started in nanoseconds, between the
     * current time and midnight, January 1, 1970 UTC.
     *
     * @return The time this pomodoro was started in nanoseconds, between the
     *         current time and midnight, January 1, 1970 UTC
     */
    public long getStartNanoseconds();

    /**
     * Returns the time in milliseconds on the pomodoro timer.
     *
     * <p>
     * This is either the time between the start and the moment this method is
     * called, or the amount of time between start and stop.
     *
     * @return The time in milliseconds
     */
    public long getMilliseconds();

    /**
     * Returns the time in nanoseconds on the pomodoro timer.
     *
     * <p>
     * This is either the time between the start and the moment this method is
     * called, or the amount of time between start and stop.
     *
     * @return The time in nanoseconds
     */
    public long getNanoseconds();

    /**
     * Returns the split time in milliseconds on the stopwatch.
     *
     * <p>
     * This is the time between start and latest split.
     *
     * @return the split time in milliseconds
     */
    public long getSplitMilliseconds();

    /**
     * Returns the split time in nanoseconds on the stopwatch.
     *
     * <p>
     * This is the time between start and latest split.
     *
     * @return the split time in nanoseconds
     */
    public long getSplitNanoseconds();
}
