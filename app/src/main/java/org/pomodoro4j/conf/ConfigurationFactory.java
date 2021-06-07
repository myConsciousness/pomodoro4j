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

import lombok.NonNull;

/**
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface ConfigurationFactory extends Serializable {

    /**
     * Returns the root configuration.
     *
     * @return The root configuration
     */
    public Configuration getInstance();

    /**
     * Returns the configuration specified by the path passed as an argument.
     *
     * @param configTreePath The path
     * @return The configuration
     */
    public Configuration getInstance(@NonNull final String configTreePath);

    /**
     * Cleans up resources acquired by this factory.
     */
    public void dispose();
}
