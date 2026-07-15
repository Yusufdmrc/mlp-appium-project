package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class BaseActions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public BaseActions(AppiumDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // -------------------- WAIT METHODS --------------------

    public WebElement waitForPresence(By locator) {
        validateLocator(locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public WebElement waitForPresence(WebElement element) {
        validateLocator(element);
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element)
        );
    }

    public WebElement waitForVisibility(By locator) {
        validateLocator(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public WebElement waitForVisibility(WebElement element) {
        validateElement(element);
        return wait.until(ExpectedConditions.visibilityOf(element)
        );
    }

    public WebElement waitForClickable(By locator) {
        validateLocator(locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public WebElement waitForClickable(WebElement element) {
        validateElement(element);
        return wait.until(ExpectedConditions.elementToBeClickable(element)
        );
    }

    public boolean waitForInvisibility(By locator) {
        validateLocator(locator);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public boolean waitForText(By locator, String expectedText) {
        validateLocator(locator);
        if (expectedText == null) {
            throw new IllegalArgumentException("Expected text cannot be null.");
        }

        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator,expectedText));
    }

    public boolean waitForAttribute(By locator, String attributeName, String expectedValue) {
        validateLocator(locator);
        if (attributeName == null || attributeName.isBlank()) {
            throw new IllegalArgumentException("Attribute name cannot be null or blank.");
        }

        if (expectedValue == null) {
            throw new IllegalArgumentException("Expected attribute value cannot be null."
            );
        }

        return wait.until(ExpectedConditions.attributeToBe(locator,attributeName,expectedValue)
        );
    }

    // -------------------- CLICK / TAP --------------------

    public void click(By locator) {
        waitForClickable(locator).click();
    }

    public void click(WebElement element) {
        waitForClickable(element).click();
    }

    // -------------------- SEND KEYS --------------------

    /**
     * Sends text without clearing the current value.
     */
    public void send(By locator, String text) {
        validateText(text);
        waitForVisibility(locator).sendKeys(text);
    }

    /**
     * Sends text without clearing the current value.
     */
    public void send(WebElement element, String text) {
        validateText(text);
        waitForVisibility(element).sendKeys(text);
    }

    /**
     * It clears the field and then enters the text.
     */
    public void clearAndSend(By locator, String text) {
        validateText(text);
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * It clears the field and then enters the text.
     */
    public void clearAndSend(WebElement element, String text) {
        validateText(text);
        WebElement visibleElement = waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    // -------------------- CLEAR ---- ----------------

    public void clear(By locator) {
        waitForVisibility(locator).clear();
    }

    public void clear(WebElement element) {
        waitForVisibility(element).clear();
    }

    // -------------------- TEXT / ATTRIBUTE --------------------

    public String getText(By locator) {return waitForVisibility(locator).getText();
    }

    public String getText(WebElement element) {return waitForVisibility(element).getText();
    }

    public String getAttribute(By locator, String attributeName) {
        validateAttributeName(attributeName);
        return waitForVisibility(locator).getAttribute(attributeName);
    }

    public String getAttribute(WebElement element, String attributeName) {
        validateAttributeName(attributeName);
        return waitForVisibility(element).getAttribute(attributeName);
    }

    // -------------------- ELEMENT STATE --------------------

    public boolean isDisplayed(By locator) {
        validateLocator(locator);
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement element
    ) {
        validateElement(element);
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (
                TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        try {
            return waitForPresence(locator).isEnabled();
        } catch (
                TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        try {
            return waitForPresence(element).isEnabled();
        } catch (
                TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isSelected(By locator) {
        try {
            return waitForPresence(locator).isSelected();

        } catch (
                TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isSelected(WebElement element) {
        try {
            return waitForPresence(element).isSelected();

        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    // -------------------- INTERNAL HELPERS --------------------

    /**
     * Searches for the element currently visible on the screen without using `Wait`.
     *
     * Used by AndroidActions and IOSActions to avoid having to wait 10 seconds each time in scroll loops.
     */

    protected Optional<WebElement> findDisplayedElement(
            By locator
    ) {
        validateLocator(locator);

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element : elements) {
            try {
                if (element.isDisplayed()) {
                    return Optional.of(element);
                }
            } catch (
                    StaleElementReferenceException ignored
            ) {
                // Scroll sonrasında element yenilenmiş olabilir.
            }
        }

        return Optional.empty();
    }

    protected String getElementId(
            WebElement element
    ) {
        validateElement(element);

        if (!(element instanceof RemoteWebElement)) {
            throw new IllegalArgumentException(
                    "Element must be an instance of RemoteWebElement."
            );
        }

        return ((RemoteWebElement) element)
                .getId();
    }

    protected void validateLocator(By locator) {
        if (locator == null) {
            throw new IllegalArgumentException(
                    "Locator cannot be null."
            );
        }
    }

    protected void validateLocator(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException(
                    "Locator cannot be null."
            );
        }
    }

    protected void validateElement(
            WebElement element
    ) {
        if (element == null) {
            throw new IllegalArgumentException(
                    "WebElement cannot be null."
            );
        }
    }

    private void validateText(String text) {
        if (text == null) {
            throw new IllegalArgumentException(
                    "Text cannot be null."
            );
        }
    }

    private void validateAttributeName(
            String attributeName
    ) {
        if (attributeName == null ||
                attributeName.isBlank()) {

            throw new IllegalArgumentException(
                    "Attribute name cannot be null or blank."
            );
        }
    }
}

