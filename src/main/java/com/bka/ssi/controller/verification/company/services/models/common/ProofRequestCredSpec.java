/*
 * Copyright 2021 Bundesrepublik Deutschland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bka.ssi.controller.verification.company.services.models.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Proof request cred spec.
 */
public class ProofRequestCredSpec {
    private List<Map<String, String>> restrictions;
    private List<String> names;

    /**
     * Add names item proof request cred spec.
     *
     * @param namesItem the names item
     * @return the proof request cred spec
     */
    public ProofRequestCredSpec addNamesItem(String namesItem) {
        if (this.names == null) {
            this.names = new ArrayList<>();
        }

        this.names.add(namesItem);
        return this;
    }

    /**
     * Add restrictions item proof request cred spec.
     *
     * @param restrictionsItem the restrictions item
     * @return the proof request cred spec
     */
    public ProofRequestCredSpec addRestrictionsItem(Map<String, String> restrictionsItem) {
        if (this.restrictions == null) {
            this.restrictions = new ArrayList<>();
        }

        this.restrictions.add(restrictionsItem);
        return this;
    }

    /**
     * Gets restrictions.
     *
     * @return the restrictions
     */
    public List<Map<String, String>> getRestrictions() {
        return restrictions;
    }

    /**
     * Gets names.
     *
     * @return the names
     */
    public List<String> getNames() {
        return names;
    }
}
