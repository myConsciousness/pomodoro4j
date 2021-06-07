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

import java.io.Serializable;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Configuration extends Serializable {

    /**
     * Sets the concentration minutes.
     *
     * @param concentrationMinutes The concentration minutes
     * @return This instance
     */
    public Configuration setConcentrationMinutes(int concentrationMinutes);

    /**
     * Sets the break minutes.
     *
     * @param breakMinutes The break minutes
     * @return This instance
     */
    public Configuration setBreakMinutes(int breakMinutes);

    /**
     * Sets the longer break minutes.
     *
     * @param longerBreakMinutes The longer break minutes
     * @return This instance
     */
    public Configuration setLongerBreakMinutes(int longerBreakMinutes);

    /**
     * Sets the count until longer break.
     *
     * @param countUntilLongerBreak The count until longer break
     * @return This instance
     */
    public Configuration setCountUntilLongerBreak(int countUntilLongerBreak);

    /**
     * Returns the concentration minutes.
     *
     * @return The concentration minutes
     */
    public int getConcentrationMinutes();

    /**
     * Returns the break minutes.
     *
     * @return The break minutes
     */
    public int getBreakMinutes();

    /**
     * Returns the longer break minutes.
     *
     * @return The longer break minutes minutes
     */
    public int getLongerBreakMinutes();

    /**
     * Returns the count until longer break.
     *
     * @return The count until longer break
     */
    public int getCountUntilLongerBreak();
}
