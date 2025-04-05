/*
 * Copyright 2025 Adiel Wesley Silva
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
package com.wesleyadiel.aiagent

class Agent(private val llm: GeminiClient) {
    fun handleInput(input: String) {
        println("User: $input")

        val response =
            llm.ask(
                "You're a personal assistant. User said: \"$input\". " +
                    "Answer something useful. Be kind but also be straightforward.",
            )

        println("Agent: $response")
    }
}
