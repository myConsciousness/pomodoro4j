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

import java.util.concurrent.TimeUnit;

import org.pomodoro4j.conf.Configuration;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class PomodoroImpl extends PomodoroBaseImpl implements Pomodoro {

    /**
     * The constructor.
     *
     * @param configuration The configuration
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    PomodoroImpl(@NonNull final Configuration configuration) {
        super(configuration);
    }

    @Override
    public boolean performs() {
        if (super.getPomodoroState() == PomodoroState.INITIALIZED) {
            super.getPomodoroTimer().start();
            super.setPomodoroState(PomodoroState.CONCENTRATING);
        }

        return super.getPomodoroState() != PomodoroState.INITIALIZED
                && super.getPomodoroState() != PomodoroState.FINISHED;
    }

    @Override
    public void stop() {
        super.getPomodoroTimer().stop();
        super.setPomodoroState(PomodoroState.STOPPED);
    }

    @Override
    public void reset() {
        super.getBreakCounter().reset();
        super.getPomodoroTimer().reset();
        super.setPomodoroState(PomodoroState.INITIALIZED);
    }

    @Override
    public long getStartMilliseconds() {
        return super.getPomodoroTimer().getStartTime();
    }

    @Override
    public long getStartNanoseconds() {
        return TimeUnit.MILLISECONDS.toNanos(super.getPomodoroTimer().getStartTime());
    }

    @Override
    public long getMilliseconds() {
        return super.getPomodoroTimer().getTime();
    }

    @Override
    public long getNanoseconds() {
        return super.getPomodoroTimer().getNanoTime();
    }

    @Override
    public long getSplitMilliseconds() {
        return super.getSplitTime().getMilliseconds();
    }

    @Override
    public long getSplitNanoseconds() {
        return super.getSplitTime().getNanoseconds();
    }
}
