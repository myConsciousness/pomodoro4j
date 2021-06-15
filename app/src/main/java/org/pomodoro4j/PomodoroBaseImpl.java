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

import org.apache.commons.lang3.time.StopWatch;
import org.pomodoro4j.conf.Configuration;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class PomodoroBaseImpl implements PomodoroBase {

    /**
     * The pomodoro timer
     */
    private static final StopWatch POMODORO_TIMER = new StopWatch();

    /**
     * The pomodoro state
     */
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private PomodoroState pomodoroState = PomodoroState.INITIALIZED;

    /**
     * The break counter
     */
    private Counter breakCounter = BreakCounter.newInstance();

    /**
     * The configuration
     */
    private Configuration configuration;

    /**
     * The constructor.
     *
     * @param configuration the configuration
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    protected PomodoroBaseImpl(@NonNull final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean shouldStartBreak() {
        this.checkState(BreakPolicy.START_PRECONDITION);
        return TimeUnit.MILLISECONDS.toMinutes(POMODORO_TIMER.getTime()) >= this.configuration
                .getConcentrationMinutes();
    }

    @Override
    public boolean isBreakOngoing() {
        this.checkState(BreakPolicy.BREAKING_PRECONDITION);
        return this.pomodoroState == PomodoroState.BREAKING || this.pomodoroState == PomodoroState.LONGER_BREAKING;
    }

    @Override
    public boolean shouldEndBreak() {
        this.checkState(BreakPolicy.BREAKING_PRECONDITION);

        if (this.pomodoroState == PomodoroState.LONGER_BREAKING) {
            return TimeUnit.MILLISECONDS.toMinutes(POMODORO_TIMER.getSplitTime()) >= this.configuration
                    .getLongerBreakMinutes();
        }

        return TimeUnit.MILLISECONDS.toMinutes(POMODORO_TIMER.getSplitTime()) >= this.configuration.getBreakMinutes();
    }

    @Override
    public void startBreak() {
        this.checkState(BreakPolicy.START_PRECONDITION);

        if (this.breakCounter.getCount() >= this.configuration.getCountUntilLongerBreak()) {
            this.breakCounter.reset();
            this.pomodoroState = PomodoroState.LONGER_BREAKING;
        } else {
            this.breakCounter.increment();
            this.pomodoroState = PomodoroState.BREAKING;
        }

        POMODORO_TIMER.split();
    }

    @Override
    public void endBreak() {
        this.checkState(BreakPolicy.END_PRECONDITION);

        if (this.pomodoroState == PomodoroState.LONGER_BREAKING) {
            this.pomodoroState = PomodoroState.FINISHED;
        } else {
            this.pomodoroState = PomodoroState.CONCENTRATING;
        }

        POMODORO_TIMER.unsplit();
    }

    /**
     * Returns the instance of {@link StopWatch} .
     *
     * @return The instance of {@link StopWatch}
     */
    protected StopWatch getPomodoroTimer() {
        return POMODORO_TIMER;
    }

    /**
     * Checks the pomodoro state. Whenever an abnormal condition is detected, an
     * exception will be raised at runtime.
     *
     * @exception PomodoroException If the Pomodoro set has not been started
     */
    private void checkState(@NonNull final BreakPolicy breakPolicy) {
        breakPolicy.checkState(this.pomodoroState);
    }
}
