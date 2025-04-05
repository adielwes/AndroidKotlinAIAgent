# 🤖 Personal Agent with LLM (Gemini) – Kotlin

This is a Kotlin-based experiment to build an **intelligent personal agent** capable of
interpreting natural language and making decisions based on user input. This initial
version focuses on **text-based interaction with a generative language model (LLM)**
using Google’s **Gemini API**.

---

## 🧠 What is an agent?

An AI agent is an entity that:

1. **Perceives** the environment (via sensors or user input)
2. **Processes** that perception to make decisions
3. **Acts** based on those decisions to perform useful tasks

In this project, the agent is **a console-based program** that interacts with the user through text,
uses an LLM to interpret commands, and returns smart responses.

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

1. Create a file named `apikeys.properties` in the root directory with your Gemini API key:
```
GEMINI_API_KEY=your_api_key_here
```

2. Run the application:
```
Use IntelliJ/Android Studio to run the main() function in Main.kt.
```

---

## 📌 Next steps

- Integrate with Google Calendar
- Local notification support
- Jetpack Compose UI