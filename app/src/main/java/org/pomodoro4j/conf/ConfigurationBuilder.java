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

import lombok.AccessLevel;
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
public final class ConfigurationBuilder {

    /**
     * The configuration base
     */
    private ConfigurationBase configurationBase = new PropertyConfiguration();

    /**
     * Returns the new instance of {@link ConfigurationBuilder} .
     *
     * @return The new instance of {@link ConfigurationBuilder}
     */
    public static ConfigurationBuilder newBuilder() {
        return new ConfigurationBuilder();
    }

    /**
     * Sets the concentration minutes.
     *
     * <p>
     * This {@link #setConcentrationMinutes(int)} will always fail at runtime if
     * this method is called after the {@link #build()} has already been called.
     *
     * @param concentrationMinutes The concentration minutes
     * @return This instance
     */
    public ConfigurationBuilder setConcentrationMinutes(int concentrationMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setConcentrationMinutes(concentrationMinutes);
        return this;
    }

    /**
     * Sets the break minutes.
     *
     * <p>
     * This {@link #setBreakMinutes(int)} will always fail at runtime if this method
     * is called after the {@link #build()} has already been called.
     *
     * @param breakMinutes The break minutes
     * @return This instance
     */
    public ConfigurationBuilder setBreakMinutes(int breakMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setBreakMinutes(breakMinutes);
        return this;
    }

    /**
     * Sets the longer break minutes.
     *
     * <p>
     * This {@link #setLongerBreakMinutes(int)} will always fail at runtime if this
     * method is called after the {@link #build()} has already been called.
     *
     * @param longerBreakMinutes The longer break minutes
     * @return This instance
     */
    public ConfigurationBuilder setLongerBreakMinutes(int longerBreakMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setLongerBreakMinutes(longerBreakMinutes);
        return this;
    }

    /**
     * Sets the count until longer break.
     *
     * <p>
     * This {@link #setCountUntilLongerBreak(int)} will always fail at runtime if
     * this method is called after the {@link #build()} has already been called.
     *
     * @param countUntilLongerBreak The count until longer break
     * @return This instance
     */
    public ConfigurationBuilder setCountUntilLongerBreak(int countUntilLongerBreak) {
        this.checkNotBuilt();
        this.configurationBase.setCountUntilLongerBreak(countUntilLongerBreak);
        return this;
    }

    /**
     * Builds the configuration object based on the parameters.
     *
     * <p>
     * This {@link #build()} will always fail at runtime if this method is called
     * after the {@link #build()} has already been called.
     *
     * @return The built configuration
     */
    public Configuration build() {
        this.checkNotBuilt();

        try {
            return this.configurationBase;
        } finally {
            this.configurationBase = null;
        }
    }

    /**
     * Checks if the configuration has already been built. If the configuration has
     * already been built, it will always fail at runtime.
     */
    private void checkNotBuilt() {
        if (this.configurationBase == null) {
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }
}
