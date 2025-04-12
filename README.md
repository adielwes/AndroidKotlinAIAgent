# 🤖 Personal Agent with LLM (Gemini) – Kotlin

This is a Kotlin-based experiment to build an **intelligent personal agent** capable of
interpreting natural language and making decisions based on user input.  The current version
features a modern Jetpack Compose UI, enabling intuitive message interaction, memory persistence,
and message classification—all running locally on the device.

---

## 🧠 What is an agent?

An AI agent is an entity that:

1. **Perceives** the environment (via sensors or user input)
2. **Processes** that perception to make decisions
3. **Acts** based on those decisions to perform useful tasks

---

## 🤖 How this project implements an AI Agent

In this project, the agent is embodied as a **conversational assistant** with a modern
Jetpack Compose UI, allowing users to interact through natural language. Behind the scenes,
the agent follows the same classic cycle:

- **Perception**  
  The user enters a message via the UI. This input is captured and sent to the agent for processing.  
  → *This corresponds to the text field and input event in the Compose interface.*

- **Reasoning**  
  The message is analyzed by a generative LLM (Gemini), which:
  - Understands the intent
  - Classifies the message (as Task, Reminder, Idea, Feeling, or Other)
  - Extracts any time-related expressions (e.g. "tomorrow at 3pm")  
    → *This is the core intelligence powered by the LLM and a custom logic.*

- **Action**  
  Based on the reasoning, the agent:
  - Responds to the user with a contextual reply
  - Saves the interaction (message, response, category, and optional deadline)
  - May schedule notifications or calendar events (in future versions)  
    → *These actions are the agent's effect on the world — even if that “world” is local, for now.*

This structure makes the agent not only reactive, but extendable — ready for deeper memory,
planning, and real-world integrations like reminders and task automation.

---

## 🚀 Features in this version

- 🧠 Natural language understanding using Gemini LLM
- 💬 Interactive command-line conversation with the agent
- 🗂️ Categorization of user input into:
    - `Task`
    - `Reminder`
    - `Idea`
    - `Feeling`
    - `Other`
- 💾 Persistent memory: message history saved and restored across sessions
- ⏳ Time detection: extracts and stores deadlines from user messages  
  (e.g. "next Monday", "in 2 hours", "tomorrow at 3pm")
- 📱 **Modern UI built with Jetpack Compose**
    - Material 3 design
    - Scrollable message list with auto-scroll
    - TopAppBar and proper keyboard handling
- 🔄 Future-ready for:
    - Google Calendar integration
    - Local notifications

## 🧠 How it works

Each time the user sends a message, the agent goes through the following steps:
1. Parses the input using the Gemini LLM to generate a contextual response
2. Classifies the user message into one of the categories:
`Task`, `Reminder`, `Idea`, `Feeling`, or `Other`
3. Detects natural language time expressions, such as:
`"tomorrow at 3pm"`, `"next Monday"`, `"in 2 hours"`, and stores them as a deadline
4. Stores the interaction as a memory entry in a local JSON file, including:
- User input
- Agent response
- Category
- Optional deadline (if applicable)
5. Loads all previous memory entries on startup, allowing the agent to retain context across
sessions and eventually act on saved information.

This evolving memory system makes it possible for the agent to:
- Answer follow-up questions like “Did I already tell you about my meeting?”
- Search past messages by category or deadline
- Prepare for integrations with tools like Google Calendar and notification systems

---

## 🛠️ Technologies used

- **Kotlin** (JVM)
- **Gson** for JSON handling
- **OkHttp** for HTTP requests
- **Google Generative AI – Gemini API**

---

## ▶️ How to run

1. Make sure you have a valid [Gemini API key](https://makersuite.google.com/app)
2. Create a file named `apikeys.properties` in the root directory with your Gemini API key:
```
GEMINI_API_KEY=your_api_key_here
```

3. Run the app in Android Studio on a device or emulator with:

- `Run > Run 'app'`
- Or click the green ▶️ next to `MainActivity`

---

## 🛠️ Tech Stack

- Kotlin (JVM)
- Jetpack Compose (Material 3)
- OkHttp for networking
- Gson for JSON serialization
- Kotlin Coroutines for asynchronous operations
- Android internal storage for local memory

---

## 📌 Tag version

This version is tagged as: `v0.2-compose-ui`
> First version with Jetpack Compose UI replacing the previous console-based interface.

---

## 📌 Next steps

- Integrate with Google Calendar
- Local notification support

---

## 📄 License

Licensed under the [Apache 2.0 License](LICENSE)