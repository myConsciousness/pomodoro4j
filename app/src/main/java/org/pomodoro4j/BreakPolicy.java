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

import lombok.NonNull;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
enum BreakPolicy {

    SHOULD_START_BREAK {
        @Override
        public void checkState(@NonNull final PomodoroState pomodoroState) {
            if (pomodoroState == PomodoroState.CONCENTRATING || pomodoroState == PomodoroState.STOPPED) {
                return;
            }

            throw new PomodoroException();
        }
    },

    IS_BREAK_ONGOING {
        @Override
        public void checkState(@NonNull final PomodoroState pomodoroState) {
            if (pomodoroState == PomodoroState.CONCENTRATING || pomodoroState == PomodoroState.BREAKING
                    || pomodoroState == PomodoroState.LONGER_BREAKING || pomodoroState == PomodoroState.STOPPED
                    || pomodoroState == PomodoroState.FINISHED) {
                return;
            }

            throw new PomodoroException();
        }
    },

    SHOULD_END_BREAK {
        @Override
        public void checkState(@NonNull final PomodoroState pomodoroState) {
            if (pomodoroState == PomodoroState.BREAKING || pomodoroState == PomodoroState.LONGER_BREAKING
                    || pomodoroState == PomodoroState.STOPPED) {
                return;
            }

            throw new PomodoroException();
        }
    },

    START_BREAK {
        @Override
        public void checkState(@NonNull final PomodoroState pomodoroState) {
            if (pomodoroState == PomodoroState.CONCENTRATING || pomodoroState == PomodoroState.STOPPED) {
                return;
            }

            throw new PomodoroException();
        }
    },

    END_BREAK {
        @Override
        public void checkState(@NonNull final PomodoroState pomodoroState) {
            if (pomodoroState == PomodoroState.BREAKING || pomodoroState == PomodoroState.LONGER_BREAKING
                    || pomodoroState == PomodoroState.STOPPED) {
                return;
            }

            throw new PomodoroException();
        }
    };

    public abstract void checkState(@NonNull final PomodoroState pomodoroState);
}
