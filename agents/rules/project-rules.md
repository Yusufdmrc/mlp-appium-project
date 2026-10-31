# Agent Rules & Guidelines

This document outlines the operational rules, coding standards, and architectural best practices that every AI Agent (and contributor) must adhere to when developing or modifying code in the **MLP Appium** project.

---

## 1. General Principles

1. **Safety First:** Never introduce breaking changes to existing test scenarios or framework utilities without explicit alignment.
2. **Preserve Documentation:** Retain existing comments, annotations, and docstrings unless directly addressing changes requested.
3. **Consistency:** Match the existing coding conventions, file naming styles, and package organization.
4. **No Secrets / Local Paths in Commits:** Do not hardcode personal credentials, passwords, tokens, or machine-specific absolute paths in code or property files.

---

## 2. Java & Framework Standards

- **Java Version:** Target Java 17 features where appropriate, while keeping code clean, readable, and idiomatic.
- **Access Modifiers & Encapsulation:**
  - Web elements declared with `@AndroidFindBy` / `@iOSXCUITFindBy` should be `private` or package-private within page objects.
  - Do not expose `WebElement` directly outside of page classes. Expose intent-revealing public methods (e.g., `clickLoginButton()`, `verifyErrorMessageIsDisplayed(String expectedMessage)`).
- **Page Object Model (POM):**
  - All UI elements and screen-specific actions belong in `src/test/java/pages/`.
  - Every Page class must initialize elements via `PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);`.
  - Maintain a reference to `BaseActions` (and `AndroidActions` / `IOSActions` if necessary) for interactions.

---

## 3. Appium & Mobile Interaction Rules

1. **Synchronization Over Hardcoded Sleeps:**
   - **NEVER** use `Thread.sleep(...)` for synchronization.
   - Use explicit waits provided by `BaseActions` (e.g., `waitForVisibility`, `waitForClickability`, `waitForElementToBeInvisible`).
2. **Cross-Platform Support (Android & iOS):**
   - Whenever locating an element, provide both `@AndroidFindBy` and `@iOSXCUITFindBy` annotations where applicable.
   - For gestures (swiping, scrolling, pinching), leverage `AndroidActions` and `IOSActions` rather than writing ad-hoc touch action sequences.
3. **Robust Locators:**
   - Prefer accessibility IDs (`accessibility = "..."`) or resource IDs over fragile, deeply nested XPaths.
   - When XPath is required, use meaningful attributes (`text`, `content-desc`, `resource-id`) and avoid dynamic or index-heavy paths (e.g., avoid `//android.widget.FrameLayout[1]/android.widget.LinearLayout[2]/...`).

---

## 4. Cucumber & BDD Rules

1. **Declarative Step Definitions:**
   - Gherkin steps should read like user business actions, not technical implementation details (e.g., prefer `When user logs in with valid credentials` over `When user clicks textfield at index 2 and types password`).
2. **Thin Step Definitions:**
   - `stepDefinitions/` classes must contain zero locator lookups and zero direct driver calls.
   - Step methods should merely parse parameters and call methods on Page Objects.
3. **Step Reusability:**
   - Check existing step definitions before writing new ones to prevent duplicate or ambiguous steps.
4. **Tags:**
   - Tag all feature files and scenarios appropriately (e.g., `@Login`, `@Smoke`, `@Regression`, `@AndroidOnly`, `@iOSOnly`).

---

## 5. Configuration & Device Execution Rules

1. **Environment Properties:**
   - Manage global settings via `src/test/resources/config.properties`.
   - Never overwrite local machine paths (like `appiumJSPath`) with paths from another developer/machine without verification.
2. **Driver Lifecycle:**
   - Driver creation and teardown must be exclusively managed by `utils.DriverFactory` and `utils.Hooks`.
   - Ensure the driver session is cleanly closed on test finish or failure in `@After` hooks.

---

## 6. Error Handling & Reporting

1. **Clear Assertion Messages:**
   - Always supply descriptive failure messages in assertions (e.g., `Assert.assertTrue(isDisplayed, "Expected balance label to be visible on Home page");`).
2. **Allure Integration:**
   - Failure screenshots are automatically captured by `Hooks.java`. Ensure any custom failure handling does not suppress or bypass Cucumber/Allure lifecycle events.

---

## 7. AI Agent Checklist Before Finalizing Changes

- [ ] Code compiles cleanly (`mvn clean test-compile`).
- [ ] No extraneous files or debug logs left in the working directory.
- [ ] Locators follow cross-platform standards (`@AndroidFindBy` & `@iOSXCUITFindBy`).
- [ ] No `Thread.sleep` introduced.
- [ ] Step definitions stay decoupled from UI locators.
- [ ] New methods and classes follow project naming conventions and structure.

