package utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import pages.LoginPage;

import java.util.Properties;


public class Hooks {
    private AppiumDriver driver;
    private Properties properties;


    private void initializeDriver(){
        String platform = getPlatform();
        if (platform == null || platform.isEmpty()) {
            throw new IllegalArgumentException("Platform parameter not found. Set via testng.xml or PLATFORM environment variable");
        }
        properties = ConfigReader.initialize_Properties();
        driver = DriverFactory.initialize_Driver(platform);
        System.out.println("Test execution started on: " + platform);
    }

    private String getPlatform() {
        // Try getting from TestNG parameter (testng.xml)
        try {
            String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");
            if (platform != null && !platform.isEmpty()) {
                return platform;
            }
        } catch (Exception e) {
            // Fallback to environment variable if TestNG parameter fails
        }
        
        // Fallback: Get from environment variable or system property
        String platform = System.getenv("PLATFORM");
        if (platform == null || platform.isEmpty()) {
            platform = System.getProperty("platform");
        }
        
        return platform != null ? platform : "IOS"; // Default to IOS
    }

    @Before(order =1, value = "not @LoginRequired")
    public void setUp() {
        initializeDriver();
    }

    @Before(order =0, value = "@LoginRequired")
    public  void setUpWithLogin() {
        initializeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("correct.tc.id"),
                ConfigReader.get("correct.password")
        );
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
            }
            driver.quit();
            System.out.println("Test execution completed. Driver closed.");
        }
    }
}
