package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class BaseActions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public BaseActions(AppiumDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // -------------------- WAIT METHODS --------------------

    /**
     * Waits for the element to be present in the DOM / native element tree.
     * The element does not need to be visible.
     */
    public WebElement waitForPresence(By locator) {
        validateLocator(locator);

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    /**
     * Waits for the element found by the locator to become visible.
     */
    public WebElement waitForVisibility(By locator) {
        validateLocator(locator);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    /**
     * Waits for the given WebElement to become visible.
     */
    public WebElement waitForVisibility(
            WebElement element
    ) {
        validateElement(element);

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    /**
     * Waits for the element found by the locator to become clickable.
     */
    public WebElement waitForClickable(By locator) {
        validateLocator(locator);

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    /**
     * Waits for the given WebElement to become clickable.
     */
    public WebElement waitForClickable(
            WebElement element
    ) {
        validateElement(element);

        return wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    /**
     * Waits for the element to become invisible or be removed from the element tree.
     */
    public boolean waitForInvisibility(By locator) {
        validateLocator(locator);

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    /**
     * Waits for the element text to contain the expected text.
     */
    public boolean waitForText(
            By locator,
            String expectedText
    ) {
        validateLocator(locator);
        validateText(expectedText);

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        locator,
                        expectedText
                )
        );
    }

    /**
     * Waits for the element attribute value to match the expected value.
     */
    public boolean waitForAttribute(
            By locator,
            String attributeName,
            String expectedValue
    ) {
        validateLocator(locator);
        validateAttributeName(attributeName);

        if (expectedValue == null) {
            throw new IllegalArgumentException(
                    "Expected attribute value cannot be null."
            );
        }

        return wait.until(
                ExpectedConditions.attributeToBe(
                        locator,
                        attributeName,
                        expectedValue
                )
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
     * Sends text without clearing the existing value in the input.
     */
    public void send(
            By locator,
            String text
    ) {
        validateText(text);

        waitForVisibility(locator)
                .sendKeys(text);
    }

    /**
     * Sends text without clearing the existing value in the input.
     */
    public void send(
            WebElement element,
            String text
    ) {
        validateText(text);

        waitForVisibility(element)
                .sendKeys(text);
    }

    /**
     * Clears the input field and then sends text.
     */
    public void clearAndSend(
            By locator,
            String text
    ) {
        validateText(text);

        WebElement element =
                waitForVisibility(locator);

        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clears the input field and then sends text.
     */
    public void clearAndSend(
            WebElement element,
            String text
    ) {
        validateText(text);

        WebElement visibleElement =
                waitForVisibility(element);

        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    // -------------------- CLEAR --------------------

    public void clear(By locator) {
        waitForVisibility(locator).clear();
    }

    public void clear(WebElement element) {
        waitForVisibility(element).clear();
    }

    // -------------------- TEXT / ATTRIBUTE --------------------

    public String getText(By locator) {
        return waitForVisibility(locator)
                .getText();
    }

    public String getText(WebElement element) {
        return waitForVisibility(element)
                .getText();
    }

    public String getAttribute(
            By locator,
            String attributeName
    ) {
        validateAttributeName(attributeName);

        return waitForVisibility(locator)
                .getAttribute(attributeName);
    }

    public String getAttribute(
            WebElement element,
            String attributeName
    ) {
        validateAttributeName(attributeName);

        return waitForVisibility(element)
                .getAttribute(attributeName);
    }

    // -------------------- ELEMENT STATE --------------------

    public boolean isDisplayed(By locator) {
        validateLocator(locator);

        try {
            return waitForVisibility(locator)
                    .isDisplayed();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isDisplayed(
            WebElement element
    ) {
        validateElement(element);

        try {
            return waitForVisibility(element)
                    .isDisplayed();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        validateLocator(locator);

        try {
            return waitForPresence(locator)
                    .isEnabled();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isEnabled(
            WebElement element
    ) {
        validateElement(element);

        try {
            return waitForVisibility(element)
                    .isEnabled();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isSelected(By locator) {
        validateLocator(locator);

        try {
            return waitForPresence(locator)
                    .isSelected();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    public boolean isSelected(
            WebElement element
    ) {
        validateElement(element);

        try {
            return waitForVisibility(element)
                    .isSelected();

        } catch (
                TimeoutException
                | NoSuchElementException
                | StaleElementReferenceException exception
        ) {
            return false;
        }
    }

    // -------------------- INTERNAL HELPERS --------------------

    /**
     * Searches for a visible element on the current screen without using explicit wait.
     *
     * This avoids waiting 10 seconds on each iteration in scroll loops.
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
                // The element tree may have refreshed after scrolling.
            }
        }

        return Optional.empty();
    }

    /**
     * Returns the element ID used for Appium gesture operations.
     */
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

    protected void validateElement(
            WebElement element
    ) {
        if (element == null) {
            throw new IllegalArgumentException(
                    "WebElement cannot be null."
            );
        }
    }

    protected void validateText(String text) {
        if (text == null) {
            throw new IllegalArgumentException(
                    "Text cannot be null."
            );
        }
    }

    protected void validateAttributeName(
            String attributeName
    ) {
        if (attributeName == null
                || attributeName.isBlank()) {

            throw new IllegalArgumentException(
                    "Attribute name cannot be null or blank."
            );
        }
    }

    // -------------------- ENUMS --------------------

    public enum SwipeDirection {

        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right");

        private final String value;

        SwipeDirection(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PickerDirection {

        NEXT("next"),
        PREVIOUS("previous");

        private final String value;

        PickerDirection(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
