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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
abstract class ConfigurationBase implements Configuration {

    /**
     * The concentration minutes
     */
    @Getter
    private int concentrationMinutes = 25;

    /**
     * The break minutes
     */
    @Getter
    private int breakMinutes = 5;

    /**
     * The longer break minutes
     */
    @Getter
    private int longerBreakMinutes = 15;

    /**
     * The count until longer break
     */
    @Getter
    private int countUntilLongerBreak = 4;

    @Override
    public Configuration setConcentrationMinutes(int concentrationMinutes) {
        this.concentrationMinutes = concentrationMinutes;
        return this;
    }

    @Override
    public Configuration setBreakMinutes(int breakMinutes) {
        this.breakMinutes = breakMinutes;
        return this;
    }

    @Override
    public Configuration setLongerBreakMinutes(int longerBreakMinutes) {
        this.longerBreakMinutes = longerBreakMinutes;
        return this;
    }

    @Override
    public Configuration setCountUntilLongerBreak(int countUntilLongerBreak) {
        this.countUntilLongerBreak = countUntilLongerBreak;
        return this;
    }
}
