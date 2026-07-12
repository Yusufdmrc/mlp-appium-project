package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    static AppiumDriver driver;
    static Properties properties;
    static BaseOptions<?> capabilities;
    static AppiumDriverLocalService service;

    public static AppiumDriver initialize_Driver(String platform) {

        properties = ConfigReader.getProperties();

        startServer();

        if (platform.equals("Android")) {

            UiAutomator2Options androidOptions = new UiAutomator2Options();

            androidOptions.setPlatformName("Android");
            androidOptions.setUdid(properties.getProperty("androidUdid"));
            androidOptions.setAppPackage(properties.getProperty("androidAppPackage"));
            androidOptions.setAppActivity(properties.getProperty("androidAppActivity"));
            androidOptions.setAutomationName("UiAutomator2");

            capabilities = androidOptions;

        } else if (platform.equals("IOS")) {

            XCUITestOptions iosOptions = new XCUITestOptions();

            iosOptions.setPlatformName("iOS");
            iosOptions.setBundleId(properties.getProperty("iosBundleId"));
            iosOptions.setUdid(properties.getProperty("iosUdid"));
            iosOptions.setAutomationName("XCUITest");
            iosOptions.setDeviceName(properties.getProperty("iosDeviceName"));
            iosOptions.setShowXcodeLog(true);
            iosOptions.setCapability(
                    "appium:xcodeOrgId",
                    properties.getProperty("iosXcodeOrgId")
            );
            iosOptions.setCapability(
                    "appium:xcodeSigningId",
                    properties.getProperty("iosXcodeSigningId")
            );
            iosOptions.setCapability(
                    "appium:updatedWDABundleId",
                    properties.getProperty("iosUpdatedWDABundleId")
            );
            iosOptions.setCapability(
                    "appium:allowProvisioningDeviceRegistration",
                    true
            );


            capabilities = iosOptions;

        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        try {
            String appiumServerUrl = properties.getProperty("appiumServerUrl");
            driver = new AppiumDriver(new URL(appiumServerUrl), capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }

        int impWait = Integer.parseInt(properties.getProperty("implicityWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impWait));

        return getDriver();
    }

    public static void startServer() {

        String appiumJSPath = properties.getProperty("appiumJSPath");
        String appiumIPAddress = properties.getProperty("appiumIPAddress");
        int appiumPort = Integer.parseInt(properties.getProperty("appiumPort"));

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumJSPath))
                .withIPAddress(appiumIPAddress)
                .usingPort(appiumPort)
                .build();

        service.start();
    }

    public static void stopServer() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        if (service != null) {
            service.stop();
            service = null;
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}