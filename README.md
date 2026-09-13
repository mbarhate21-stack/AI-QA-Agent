# AI QA Agent

An AI-powered software testing assistant built with Java, TestNG, Maven, and multiple AI providers.

The project demonstrates how AI can assist software testers by generating structured test cases from requirements, validating AI responses, prioritising test cases, and generating professional QA reports.

---

## 🚀 Project Overview

The **AI QA Agent** accepts a software requirement and uses an AI provider to generate structured software test cases.

The generated response is then processed through a QA pipeline that:

1. Validates the AI response
2. Checks requirement relevance
3. Parses structured test cases
4. Prioritises test cases
5. Generates QA execution reports
6. Supports multiple AI providers
7. Supports offline testing using Mock AI

The project is designed to demonstrate practical **AI Testing / AI Quality Engineering** concepts.

---

## ✨ Key Features

### 🤖 AI Test Case Generation

Generates structured test cases from natural-language software requirements.

Example:

```text
Requirement:
The user should be able to log in with a valid username and password.