package utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;


import java.util.Properties;

public class Hooks {
    private AppiumDriver driver;
    private Properties properties;


    private void initializeDriver(){
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");
        properties = ConfigReader.initialize_Properties();
        driver = DriverFactory.initialize_Driver(platform);
        System.out.println("Test execution started on: " + platform);
    }

    @Before(order =1,value = "not @LoginRequired")
    public void setUp() {
        initializeDriver();
    }

    public  void setUpWithLogin() {
        initializeDriver();
        LoginPage loginPage = new LoginPage(driver);
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
