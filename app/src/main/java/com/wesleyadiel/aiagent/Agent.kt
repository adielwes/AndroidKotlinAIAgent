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

import com.wesleyadiel.aiagent.model.MemoryEntry
import java.util.Locale

class Agent(private val llm: GeminiClient, private val memoryManager: MemoryManager) {
    private val history = memoryManager.loadMemory()

    fun handleInput(input: String) {
        println("User: $input")

        // if (answerBasedOnMemoryAndCategory(input)) return

        val category = classifyText(input)
        println("- Detected category: $category")

        val deadline = if (category == "Task" || category == "Reminder")
            extractData(input) else null
        if (deadline != null)
            println("- Detected deadline: $deadline")

        println("History size: ${history.size}")

        val response = llm.askWithMemory(history, input)
        println("Agent: $response")

        history.add(MemoryEntry(input, response, category, deadline))

        if (history.size > 20) {
            history.removeAt(0)
        }

        memoryManager.saveMemory(history)
    }

    private fun answerBasedOnMemoryAndCategory(input: String): Boolean {
        val requestedCategory = detectRequestedCategory(input)
        if (requestedCategory != null) {
            val results = memoryManager.searchByCategory(requestedCategory)

            if (results.isEmpty()) {
                println("Agent: I did not find anything about $requestedCategory.")
            } else {
                println("Agent: Here's what I found in the category '$requestedCategory':")
                results.forEach {
                    println("- ${it.user}")
                }
            }

            return true
        }
        return false
    }

    private fun detectRequestedCategory(input: String): String? {
        val map =
            mapOf(
                "tasks" to "Task",
                "my tasks" to "Task",
                "reminders" to "Reminder",
                "Can you remember" to "Reminder",
                "ideas" to "Idea",
                "my idea" to "Idea",
                "feelings" to "Feeling",
                "How I'm feeling" to "Feeling",
            )

        return map.entries.firstOrNull { (key, _) ->
            input.contains(key, ignoreCase = true)
        }?.value
    }

    fun classifyText(text: String): String {
        val prompt =
            """
            Classify the following tex in one of the categories: Task, Reminder, Idea, Feeling or Other.
            Text: "$text"
            Return just the exact category, without explanations.
            """.trimIndent()

        val resposta = llm.askWithMemory(emptyList(), prompt)
        return resposta.replace(Regex("[^\\w]"), "")
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
    }

    fun extractData(text: String): String? {
        val prompt =
            """
            Extract any data and hour from the following text: "$text".
            Return just data/hour in a clear and precise manner.
            If there is not any, answer "nothing".
            """.trimIndent()

        val response = llm.askWithMemory(emptyList(), prompt)
        return if (response.contains("nothing", ignoreCase = true)) null else response.trim()
    }
}
