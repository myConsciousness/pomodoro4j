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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance", access = AccessLevel.PACKAGE)
final class BreakCounter implements Counter {

    /**
     * The count
     */
    @Getter
    private int count;

    @Override
    public Counter increment() {
        this.count++;
        return this;
    }

    @Override
    public Counter decrement() {
        this.count--;
        return this;
    }

    @Override
    public void reset() {
        this.count = 0;
    }
}
