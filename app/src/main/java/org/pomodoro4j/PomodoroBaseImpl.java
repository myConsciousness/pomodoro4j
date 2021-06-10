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

import org.apache.commons.lang3.time.StopWatch;
import org.pomodoro4j.conf.Configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public abstract class PomodoroBaseImpl implements PomodoroBase {

    /**
     * The stop watch
     */
    private static final StopWatch STOP_WATCH = new StopWatch();

    /**
     * The configuration
     */
    private Configuration configuration;

    @Override
    public boolean shouldStartBreak() {
        return false;
    }

    @Override
    public boolean isBreakOngoing() {
        return false;
    }

    @Override
    public boolean shouldEndBreak() {
        return false;
    }

    /**
     * Returns the instance of {@link StopWatch} .
     *
     * @return The instance of {@link StopWatch}
     */
    protected StopWatch getStopWatch() {
        return STOP_WATCH;
    }
}
