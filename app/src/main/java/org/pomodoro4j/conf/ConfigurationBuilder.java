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
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
public final class ConfigurationBuilder {

    /**
     * The configuration base
     */
    private ConfigurationBase configurationBase = new PropertyConfiguration();

    public ConfigurationBuilder setConcentrationMinutes(int concentrationMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setConcentrationMinutes(concentrationMinutes);
        return this;
    }

    public ConfigurationBuilder setBreakMinutes(int breakMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setBreakMinutes(breakMinutes);
        return this;
    }

    public ConfigurationBuilder setLongerBreakMinutes(int longerBreakMinutes) {
        this.checkNotBuilt();
        this.configurationBase.setLongerBreakMinutes(longerBreakMinutes);
        return this;
    }

    public ConfigurationBuilder setCountUntilLongerBreak(int countUntilLongerBreak) {
        this.checkNotBuilt();
        this.configurationBase.setCountUntilLongerBreak(countUntilLongerBreak);
        return this;
    }

    public Configuration build() {
        this.checkNotBuilt();

        try {
            return this.configurationBase;
        } finally {
            this.configurationBase = null;
        }
    }

    private void checkNotBuilt() {
        if (this.configurationBase == null) {
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }
}
