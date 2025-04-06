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
package com.wesleyadiel.aiagent.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class MemoryManager(private val context: Context) {
    private val gson = Gson()
    private val fileName = "memory.json"

    private fun getMemoryFile(): File {
        return File(context.filesDir, fileName)
    }

    fun loadMemory(): MutableList<MemoryEntry> {
        val file = getMemoryFile()
        if (!file.exists()) return mutableListOf()

        val json = file.readText()
        val type = object : TypeToken<MutableList<MemoryEntry>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveMemory(history: List<MemoryEntry>) {
        val json = gson.toJson(history)
        getMemoryFile().writeText(json)
    }

    fun searchByCategory(category: String): List<MemoryEntry> {
        val history = loadMemory()
        return history.filter {
            it.category.equals(category, ignoreCase = true)
        }
    }
}
