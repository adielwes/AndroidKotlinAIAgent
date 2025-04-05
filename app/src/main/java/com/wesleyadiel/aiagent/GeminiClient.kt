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

import com.google.gson.Gson
import com.wesleyadiel.aiagent.model.GeminiRequest
import com.wesleyadiel.aiagent.model.GeminiResponse
import com.wesleyadiel.aiagent.model.MemoryEntry
import com.wesleyadiel.aiagent.model.Message
import com.wesleyadiel.aiagent.model.Part
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class GeminiClient(private val apiKey: String) {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val endpoint =
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=$apiKey"

    fun askWithMemory(
        history: List<MemoryEntry>,
        userInput: String,
    ): String {
        val allMessages =
            history.flatMap { (user, assistant) ->
                listOf(
                    Message(role = "user", parts = listOf(Part(user))),
                    Message(role = "model", parts = listOf(Part(assistant))),
                )
            }.toMutableList()

        allMessages.add(Message(role = "user", parts = listOf(Part(userInput))))

        val requestBodyObj = GeminiRequest(contents = allMessages)
        val json = gson.toJson(requestBodyObj)

        val body = json.toRequestBody("application/json".toMediaTypeOrNull())

        val request =
            Request.Builder()
                .url(endpoint)
                .post(body)
                .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string() ?: return "Error: empty response"
            val responseJson = gson.fromJson(responseBody, GeminiResponse::class.java)

            return responseJson
                ?.candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text
                ?: "Couldn't understand the request."
        }
    }
}
