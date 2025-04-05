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
- 💾 Message history saved locally to a JSON file
- 🔁 Restores conversation history across sessions

## 🧠 How it works

Each time the user sends a message, the agent:

1. Sends the message to the Gemini LLM to generate a contextual response
2. Uses the LLM to classify the message into a category: Task, Reminder, Idea, Feeling, or Other
3. Saves both the user input and the assistant response, along with the category, in a local JSON file
4. Loads the message history on startup to maintain memory across sessions

⚠️ This version does **not yet** include:

- User interface (UI)
- External tool integrations (e.g. Google Calendar)

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

## 📌 Next steps

- Integrate with Google Calendar
- Local notification support
- Jetpack Compose UI