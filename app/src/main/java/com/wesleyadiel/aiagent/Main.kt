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

fun main() {
    val apiKey = BuildConfig.GEMINI_API_KEY
    val llm = GeminiClient(apiKey)
    val memoryManager = MemoryManager()
    val agent = Agent(llm, memoryManager)

    println("Welcome! Say something or type 'bye' any time to leave the chat.\n")

    var userInput = ""

    while (userWantsToKeepChatting(userInput)) {
        print("You: ")
        userInput = readlnOrNull() ?: break
        if (userWantsToKeepChatting(userInput)) {
            agent.handleInput(userInput)
        } else {
            println("Agent: Bye!")
        }
    }
}

private fun userWantsToKeepChatting(userInput: String) = !userInput.lowercase().contains("bye")
