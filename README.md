# MLP Appium Test Automation

Mobile test automation project for the Milli Piyango application, built with Java, Appium, Cucumber, and TestNG. The project manages iOS and Android test flows using the Page Object Model approach.

## Technology stack

- Java 17
- Maven
- Appium Java Client 9.1.0
- Appium Server
- Selenium 4.18.0
- Cucumber-JVM 7.8.0
- TestNG 7.8.0
- Allure Report
- SLF4J / Log4j-compatible logging

## Test scope

Feature files are located under `src/test/resources/features`:

- `Login.feature`: successful, invalid-credential, and empty-field login checks
- `DrawResults.feature`: validation of draw results using game filters
- `GamingHistory.feature`: ticket history, filtering, and ticket details
- `Withdrawal.feature`: adding an IBAN, withdrawing money, and removing an IBAN

## Project structure

```text
src/test/
├── java/
│   ├── pages/           # Page Object classes
│   ├── stepDefinitions/ # Cucumber step definitions
│   ├── testRunners/     # TestRunner and FailedTestRunner
│   └── utils/           # DriverFactory, Hooks, config, and common actions
└── resources/
    ├── features/        # Cucumber feature files
    ├── testng.xml       # TestNG platform parameter and suite definition
    └── allure.properties
```

## Requirements

- JDK 17 or later
- Maven 3.9 or later
- Node.js
- Appium and the required drivers
- Android SDK, ADB, and a connected Android device/emulator for Android tests
- macOS, Xcode, WebDriverAgent signing configuration, and a connected device for iOS tests

The Appium application is started locally by `DriverFactory`. Therefore, `appiumJSPath` must point to the Appium JavaScript file on the local machine.

## Configuration

The tests read `src/test/resources/config.properties`. This file is ignored by Git and must not be committed to the repository.

Example structure:

```properties
appiumJSPath=/absolute/path/to/appium/build/lib/main.js
appiumIPAddress=127.0.0.1
appiumPort=4723
appiumServerUrl=http://127.0.0.1:4723
implicitWait=10

androidUdid=<android-device-id>
androidAppPackage=<android-app-package>
androidAppActivity=<android-app-activity>

iosUdid=<ios-device-udid>
iosBundleId=<ios-bundle-id>
iosDeviceName=<ios-device-name>
iosXcodeOrgId=<apple-development-team-id>
iosXcodeSigningId=Apple Development
iosUpdatedWDABundleId=<wda-bundle-id>

correct.tc.id=<test-user-id>
correct.account.no=<test-account-number>
correct.email=<test-email>
correct.password=<test-password>
iban.no=<test-iban>
short.name1=<iban-short-name>
price1=<withdrawal-price>
```

Do not commit test-user credentials, passwords, IBANs, or signing information to source control.

## Running the tests

The default platform is iOS. To run all tests with Maven and generate the Allure report automatically:

```bash
mvn clean test
```

Run Android tests:

```bash
mvn clean test -Dplatform=Android
```

Run iOS tests:

```bash
mvn clean test -Dplatform=IOS
```

To validate compilation without running tests:

```bash
mvn -DskipTests test-compile
```

To rerun failed scenarios listed in `target/failedrerun.txt`:

```bash
mvn -Dtest=FailedTestRunner test
```

## Reporting

### Allure Report

Allure is the primary reporting tool for this project. Cucumber results are collected under `target/allure-results`, and an HTML report is generated automatically at the end of the test lifecycle.

```text
target/site/allure-maven-plugin/index.html
```

After `mvn test` or `mvn clean test` finishes, the report path is printed in the terminal:

```text
Allure report: file:///.../target/site/allure-maven-plugin/index.html
```

If the tests are run directly from an IDE, the report can be generated manually:

```bash
mvn allure:report
```

For failed scenarios, a screenshot is attached to the Cucumber scenario through `Hooks.java` and can be viewed in the Allure report.

### Cucumber HTML

Basic HTML reports are generated at:

- `Reports/CucumberReport/Reports.html`
- `Reports/CucumberReport/FailedReports.html`

### Terminal output

Cucumber’s `pretty` and `summary` plugins display test steps and summary results in the terminal.

## Runners

- `TestRunner`: runs all feature files, generates Allure/Cucumber results, and writes failed scenarios to the rerun list.
- `FailedTestRunner`: reruns the failed scenarios listed in `target/failedrerun.txt`.

## Platform selection

The platform is resolved in the following order:

1. The `platform` parameter in the TestNG XML
2. The `PLATFORM` environment variable
3. The `-Dplatform=IOS|Android` system property
4. `IOS` by default

The default platform in `src/test/resources/testng.xml` is `IOS`.

## Security and contribution guidelines

- Do not commit `config.properties`.
- Do not put real user credentials in feature files or this README.
- Keep the separation between Page Objects, Step Definitions, and feature files.
- Do not add test results or generated report folders to source control.
