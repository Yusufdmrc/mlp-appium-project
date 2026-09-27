package testRunners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features ={"src/test/resources/features"},
        glue = {"stepDefinitions", "utils"},
        plugin = {
                "summary",
                "pretty",
                "html:Reports/CucumberReport/Reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failedrerun.txt"
        }

)

public class TestRunner extends AbstractTestNGCucumberTests {
}
