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
package com.wesleyadiel.aiagent.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wesleyadiel.aiagent.agent.Agent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreen(agent: Agent) {
    var inputState by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<MessageEntry>() }
    val isLoading = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        val index = if (messages.isEmpty()) 0 else messages.size - 1
        listState.animateScrollToItem(index)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Agent") },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .imePadding(),
        ) {
            LazyColumn(
                state = listState,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(messages) { message ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .background(Color.LightGray),
                    ) {
                        Text(
                            text = "You: ${message.user}",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Agent: ${message.assistant}",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = inputState,
                onValueChange = { inputState = it },
                label = { Text("Type your message...") },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                singleLine = true,
                enabled = !isLoading.value
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedContent(
                    targetState = isLoading.value,
                    label = "SendButtonAnimation"
                ) { loading ->
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Button(
                            onClick = {
                                val input = inputState
                                if (input.isNotBlank()) {
                                    isLoading.value = true
                                    scope.launch {
                                        val response = agent.handleInput(input)
                                        messages.add(MessageEntry(input, response))
                                        inputState = ""
                                        isLoading.value = false
                                        listState.animateScrollToItem(messages.size)
                                    }
                                }
                            },
                            enabled = !isLoading.value
                        ) {
                            Text("Send")
                        }
                    }
                }
            }
        }
    }
}

data class MessageEntry(
    val user: String,
    val assistant: String,
)
