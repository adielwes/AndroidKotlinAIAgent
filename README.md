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

- Interaction via command line
- Integration with **Gemini 2.0 Flash model** from Google's API
- Natural language interpretation and response

⚠️ This version does **not yet** include:

- User interface (UI)
- Conversation memory or history
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

- Add conversation memory
- Classify messages by type (task, idea, reminder, etc.)
- Integrate with Google Calendar
- Local notification support
- Jetpack Compose UI