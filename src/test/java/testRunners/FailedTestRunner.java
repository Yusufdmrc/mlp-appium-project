package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        glue = {"stepDefinitions", "utils"},
        plugin = {
                "summary",
                "pretty",
                "html:Reports/CucumberReport/FailedReports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {
}
