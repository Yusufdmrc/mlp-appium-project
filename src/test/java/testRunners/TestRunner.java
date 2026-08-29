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
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"
        }

)

public class TestRunner extends AbstractTestNGCucumberTests {
}