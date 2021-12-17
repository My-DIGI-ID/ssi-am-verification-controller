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

package com.bka.ssi.controller.verification.company.aop.exceptions;

/**
 * The type Mongo db configuration exception.
 */
public class MongoDbConfigurationException extends RuntimeException {
    /**
     * Instantiates a new Mongo db configuration exception.
     */
    public MongoDbConfigurationException() {
        super("Exception occurred while configuring the MongoDB client");
    }
}
