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

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface BreakSupport {

    /**
     * Checkes if a break should be started.
     *
     * @return {@code true} if a break should be started, otherwise {@code false}
     */
    public boolean shouldStartBreak();

    /**
     * Checkes if a break is ongoing.
     *
     * @return {@code true} if a break is ongoing, otherwise {@code false}
     */
    public boolean isBreaking();

    /**
     * Checkes if a break should be ended.
     *
     * @return {@code true} if a break should be ended, otherwise {@code false}
     */
    public boolean shouldEndBreak();

    /**
     * Starts the break.
     */
    public void startBreak();

    /**
     * Ends the break.
     */
    public void endBreak();

    /**
     * Starts the break if should start.
     */
    public void startBreakIfShould();

    /**
     * Ends the break if should end.
     */
    public void endBreakIfShould();
}
