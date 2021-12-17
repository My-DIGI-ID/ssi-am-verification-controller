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

/**
 * The type Persona.
 */
public class Persona {

    private String title;
    private String firstName;
    private String lastName;

    /**
     * Instantiates a new Persona.
     *
     * @param title     the title
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Persona(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new Persona.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Persona(String firstName, String lastName) {
        this.title = "No title";
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
}
