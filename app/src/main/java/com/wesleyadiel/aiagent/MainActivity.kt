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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.wesleyadiel.aiagent.agent.Agent
import com.wesleyadiel.aiagent.agent.GeminiClient
import com.wesleyadiel.aiagent.data.MemoryManager
import com.wesleyadiel.aiagent.ui.mainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        val gemini = GeminiClient(BuildConfig.GEMINI_API_KEY)
        val memoryManager = MemoryManager(this)
        val agent = Agent(gemini, memoryManager)

        setContent {
            MaterialTheme {
                Surface {
                    mainScreen(agent = agent)
                }
            }
        }
    }
}
