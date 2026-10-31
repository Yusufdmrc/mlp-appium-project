# Agent.MD - AI Coding Agent Guidelines for MLP Appium

Welcome to the **MLP Appium** repository. This document serves as the primary technical context and operational guide for AI agents (and human contributors) interacting with this codebase.

---

## 1. Project Overview

- **Project Name:** MLP Appium Test Automation (`mlp-appium`)
- **Domain:** Mobile Test Automation for the Milli Piyango mobile application (Android & iOS).
- **Core Frameworks & Tools:**
  - **Language:** Java 17
  - **Build Tool:** Apache Maven
  - **Automation Library:** Appium Java Client (v9.1.0) & Selenium WebDriver (v4.18.0)
  - **BDD Framework:** Cucumber-JVM (v7.8.0)
  - **Test Runner & Assertions:** TestNG (v7.8.0)
  - **Reporting:** Allure Reporting
  - **Design Pattern:** Page Object Model (POM) with dual-platform annotations (`@AndroidFindBy` & `@iOSXCUITFindBy`)

---

## 2. Directory Structure

```text
mlp-appium/
├── Agent.MD                            # Agent context & guidelines (this file)
├── agents/
│   ├── rules.md                        # AI Agent behavioral and coding rules
│   └── rules                           # Plaintext/symlink mirror of agent rules
├── pom.xml                             # Maven build and dependency configuration
├── README.md                           # Human-facing project documentation
└── src/
    └── test/
        ├── java/
        │   ├── pages/                  # Page Object classes (POM)
        │   │   ├── DrawResultsPage.java
        │   │   ├── GamingHistoryPage.java
        │   │   ├── LoginPage.java
        │   │   └── WithdrawalPage.java
        │   ├── stepDefinitions/        # Cucumber step definitions mapping Gherkin steps
        │   │   ├── DrawResultsSteps.java
        │   │   ├── GamingHistorySteps.java
        │   │   ├── LoginSteps.java
        │   │   └── WithdrawalSteps.java
        │   ├── testRunners/            # Test execution entry points (TestNG runners)
        │   │   ├── TestRunner.java
        │   │   └── FailedTestRunner.java
        │   └── utils/                  # Framework infrastructure & helper utilities
        │       ├── BaseActions.java    # Shared mobile interactions & explicit waits
        │       ├── AndroidActions.java # Android-specific gestures & actions
        │       ├── IOSActions.java     # iOS-specific gestures & actions
        │       ├── ConfigReader.java   # Property file loader
        │       ├── DriverFactory.java  # Appium server management & driver instantiation
        │       ├── Hooks.java          # Cucumber lifecycle hooks (Setup/Teardown/Screenshot)
        │       └── LoginHelper.java    # Common auth helpers
        └── resources/
            ├── config.properties       # Local environment and device configurations
            ├── cucumber.properties     # Cucumber runtime flags and plugins
            ├── allure.properties       # Allure reporting configuration
            ├── testng.xml              # TestNG suite suite definition & parameters
            └── features/               # Cucumber Gherkin feature files (.feature)
                ├── DrawResults.feature
                ├── GamingHistory.feature
                ├── Login.feature
                └── Withdrawal.feature
```

---

## 3. Core Architecture & Standards

### 3.1 Page Object Model (POM)

- Each application screen or component must have a dedicated class inside `src/test/java/pages/`.
- Element locators must support cross-platform execution where possible using Appium PageFactory:
  ```java
  @AndroidFindBy(...)
  @iOSXCUITFindBy(...)
  private WebElement exampleElement;
  ```
- Initialize elements in constructors via:
  ```java
  PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
  this.baseActions = new BaseActions(driver);
  ```
- Page methods should encapsulate actions and verifications; never expose raw `WebElement` objects directly to step definitions.

### 3.2 Cucumber BDD Layer

- Feature files reside in `src/test/resources/features/`.
- Step definitions reside in `src/test/java/stepDefinitions/`.
- Step definitions should be thin, delegating all UI interaction and logic to Page Objects.

### 3.3 Driver & Device Management

- Driven by `utils.DriverFactory`.
- Device UDIDs, app packages/activities, bundle IDs, and Appium server configurations are read from `config.properties`.
- Execution platform is controlled via the `platform` parameter in `testng.xml` or CLI properties (`-Dplatform=android` or `-Dplatform=ios`).

---

## 4. Common Commands

- **Compile and validate project:**
  ```bash
  mvn clean test-compile
  ```
- **Execute test suite via TestNG:**
  ```bash
  mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
  ```
- **Run specific Cucumber tag:**
  ```bash
  mvn test -Dcucumber.filter.tags="@Login"
  ```
- **Generate Allure report:**
  ```bash
  allure serve target/allure-results
  # or generate static report:
  mvn allure:report
  ```

---

## 5. Agent Instructions & Rules

Detailed behavioral, coding, and modification rules for AI agents are defined in:
👉 [`agents/rules.md`](agents/rules/project-rules.md)

Always review and strictly follow `agents/rules/project-rules.md` before making any code modifications or introducing new features to this repository.
