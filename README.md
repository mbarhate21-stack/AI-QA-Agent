# 🤖 AI QA Agent

An AI-powered software testing assistant built with **Java, TestNG, Maven, and multiple AI providers**.

The project demonstrates practical **AI Testing and AI Quality Engineering** concepts by using AI to generate structured software test cases and applying a QA validation pipeline to assess, prioritise, and report the generated results.

---

## 🚀 Project Overview

The **AI QA Agent** accepts a software requirement and uses an AI provider to generate structured software test cases.

[![Java](https://img.shields.io/badge/Java-26-orange)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red)](https://testng.org/)
[![Tests](https://img.shields.io/badge/Tests-58%20Passed-brightgreen)](https://github.com/mbarhate21-stack/AI-QA-Agent)
[![License](https://img.shields.io/badge/License-Educational-blue)](https://github.com/mbarhate21-stack/AI-QA-Agent)

The generated response is then processed through a QA pipeline that:

1. Validates the AI response
2. Checks requirement relevance
3. Parses structured test cases
4. Validates test-case quality
5. Prioritises test cases based on risk
6. Generates professional QA reports

The project demonstrates how AI can be integrated into practical software testing workflows while maintaining traditional QA principles.

---

## ✨ Key Features

* 🤖 AI-powered test-case generation
* ✅ AI response validation
* 🎯 Requirement relevance validation
* 🧪 Test-case quality validation
* 📊 Risk-based test-case prioritisation
* 🔐 Security test scenarios
* ⚠️ Positive, negative, boundary, and security scenarios
* 📄 Professional TXT and JSON QA reports
* 🔌 Multiple AI provider support
* 🧩 Mock AI support for offline testing
* 🏗️ AI provider factory architecture
* 🧪 Automated TestNG validation suite
* 🔒 Environment-variable based API-key management

---

## 🔄 AI QA Workflow

```text
Software Requirement
        ↓
    AI Provider
        ↓
AI-Generated Response
        ↓
 Response Validation
        ↓
Requirement Relevance Check
        ↓
 Test-Case Validation
        ↓
 Test-Case Parsing
        ↓
Test-Case Prioritisation
        ↓
   QA Report
```

---

## 🏗️ Architecture

The project uses a layered QA architecture to separate AI communication, test-case processing, validation, prioritisation, and reporting.

```text
                    ┌─────────────────────┐
                    │ Software Requirement│
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │   AIClientFactory   │
                    └──────────┬──────────┘
                               ↓
             ┌─────────────────┼─────────────────┐
             ↓                 ↓                 ↓
      ┌────────────┐    ┌────────────┐    ┌────────────┐
      │ Mock Client│    │ OpenAI     │    │ DeepSeek   │
      └────────────┘    └────────────┘    └────────────┘
                               │
                               ↓
                    ┌─────────────────────┐
                    │ AI Response         │
                    │ Validation          │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Requirement         │
                    │ Relevance           │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Test Case Parser    │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Test Case Validator │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Test Case           │
                    │ Prioritiser         │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ QA Report Generator │
                    └─────────────────────┘
```

---

## 🛠️ Technology Stack

| Technology   | Purpose                         |
| ------------ | ------------------------------- |
| Java 26      | Application development         |
| Maven        | Build and dependency management |
| TestNG       | Test automation and validation  |
| Jackson      | JSON parsing and processing     |
| OpenAI API   | AI provider integration         |
| DeepSeek API | AI provider integration         |
| REST API     | AI API communication            |
| JSON         | Structured AI responses         |
| Git          | Version control                 |
| GitHub       | Source-code hosting             |

---

## 🧪 Testing Capabilities

The project demonstrates multiple software testing concepts.

### Functional Test Scenarios

* Positive test cases
* Negative test cases
* Boundary test cases
* Security test cases

### AI Response Testing

The AI-generated response is validated for:

* Valid JSON structure
* Required fields
* Correct data types
* Valid test-case types
* Test-case completeness
* Requirement relevance
* Invalid or malformed responses

### Test-Case Quality Validation

Generated test cases are checked for meaningful information such as:

* Test-case ID
* Title
* Test type
* Preconditions
* Test steps
* Test data
* Expected result

---

## 🎯 Test-Case Prioritisation

The AI QA Agent prioritises generated test cases according to their testing importance.

| Test Case Type | Priority |
| -------------- | -------- |
| Security       | P1       |
| Negative       | P2       |
| Boundary       | P3       |
| Positive       | P4       |

This demonstrates a basic **risk-based testing approach**, where higher-risk scenarios are considered before lower-risk scenarios.

> **Note:** The priority model is a demonstration of risk-based prioritisation and can be extended with business impact, likelihood, severity, and production risk.

---

## 📊 QA Reporting

The project generates structured QA reports containing:

* Requirement
* Execution status
* Error count
* QA summary
* Total test cases
* Test-case priorities
* Test-case types
* Preconditions
* Test steps
* Test data
* Expected results

Reports can be generated in:

* `.txt`
* `.json`

### Example QA Summary

```text
==============================================
                  QA SUMMARY
==============================================

Requirement Status : PASS
Total Test Cases   : 4
P1 Test Cases      : 1
P2 Test Cases      : 1
P3 Test Cases      : 1
P4 Test Cases      : 1
Errors             : 0
```

---

## 🔌 Multiple AI Provider Architecture

The project supports multiple AI providers through a common `AIClient` interface and an `AIClientFactory`.

Supported providers:

```text
                 AIClient
                    │
          ┌─────────┼─────────┐
          ↓         ↓         ↓
        MOCK      OPENAI   DEEPSEEK
```

This architecture allows the AI provider to be changed without modifying the core QA workflow.

### Provider Selection

The provider can be selected using:

```text
AI_PROVIDER
```

Supported values:

```text
MOCK
OPENAI
DEEPSEEK
```

If no provider is configured, the project uses the **Mock AI Client** by default.

---

## 🧩 Mock AI Support

The project includes a `MockAIClient` so that the QA workflow can be executed without depending on paid AI API credits.

This provides:

* Offline testing
* Repeatable test results
* Faster automated testing
* No API cost during normal test execution
* Stable CI-friendly test execution

The live OpenAI and DeepSeek integrations are kept separate from the normal automated test suite because they require active API access.

---

## 🔐 Security

API keys are **not stored in the source code**.

The project uses environment variables for live AI integrations.

### OpenAI

```text
OPENAI_API_KEY
```

### DeepSeek

```text
DEEPSEEK_API_KEY
```

### AI Provider

```text
AI_PROVIDER
```

Example:

```text
AI_PROVIDER=MOCK
```

For security reasons, API keys should never be committed to Git or GitHub.

---

## 🤖 Live AI Integrations

The project contains integrations for:

* OpenAI
* DeepSeek

Live AI integrations require valid API credentials and available API usage/credits.

The default automated test suite therefore uses the Mock AI client to keep testing:

* Reliable
* Repeatable
* Cost-free
* Suitable for automated execution

---

## 💡 Example Use Case

A tester provides a requirement such as:

```text
The user should be able to log in using a valid username
and password.
```

The AI QA Agent can generate structured test cases such as:

```text
TC001 | POSITIVE
Login with valid credentials

TC002 | NEGATIVE
Login with invalid password

TC003 | BOUNDARY
Login with empty username

TC004 | SECURITY
Login with SQL injection input
```

The generated test cases are then:

```text
Generated
    ↓
Validated
    ↓
Checked for relevance
    ↓
Parsed
    ↓
Prioritised
    ↓
Reported
```

---

## 📚 QA Concepts Demonstrated

This project demonstrates practical knowledge of:

* Software Testing
* Test Case Design
* Positive Testing
* Negative Testing
* Boundary Value Testing
* Security Testing
* API Testing
* JSON Validation
* Requirement Validation
* Risk-Based Testing
* Test Case Prioritisation
* Automated Testing
* TestNG
* Maven
* AI Testing
* AI Quality Engineering
* API Integration
* Provider Abstraction
* Test Reporting

---

## 🧪 Test Automation

The project uses **TestNG** to automate validation of the application's major components.

The automated suite covers areas such as:

* AI client factory
* AI QA agent
* QA pipeline
* AI response validation
* AI quality validation
* Test-case parsing
* Test-case prioritisation
* QA report generation
* Provider selection

Run the complete suite using:

```bash
mvn clean test
```

---

## 📈 Test Results

### Latest Verified Result

```text
Tests run: 58
Failures: 0
Errors: 0
Skipped: 0
```

### Result Summary

```text
58 Tests
   ↓
58 Passed
   ↓
0 Failed
   ↓
0 Errors
   ↓
0 Skipped
```

This provides automated verification of the project's:

* AI testing workf
