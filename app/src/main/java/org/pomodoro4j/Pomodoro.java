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
     * Returns the start time.
     *
     * @return The start time
     */
    public long getStartTime();

    /**
     * Returns the time.
     *
     * @return The time
     */
    public long getTime();

    /**
     * Returns the split time.
     *
     * @return The split time
     */
    public long getSplitTime();
}
