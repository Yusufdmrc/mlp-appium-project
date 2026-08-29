package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IOSActions extends BaseActions{

    private static final int DEFAULT_MAX_SWIPES = 10;
    private static final Duration DEFAULT_LONG_PRESS_DURATION = Duration.ofSeconds(2);
    private static final double DEFAULT_DRAG_DURATION_SECONDS = 0.75;
    private static final double DEFAULT_PICKER_OFFSET = 0.20;
    private static final int DEFAULT_PICKER_MAX_ATTEMPTS = 25;


    private final IOSDriver iosDriver;

    public IOSActions(IOSDriver driver) {
        super(driver);
        this.iosDriver = driver;
    }

    // -------------------- SCROLL --------------------

    /**
     * Scrolls the content on the screen downward.
     *
     * In terms of user interaction, the finger is swiped upward.
     */
    public void scrollDown() {
        swipeScreen(SwipeDirection.UP);
    }

    /**
     * Scrolls the content on the screen upward.
     *
     * In terms of user interaction, the finger is swiped downward.
     */
    public void scrollUp() {
        swipeScreen(SwipeDirection.DOWN);
    }

    /**
     * Scrolls until the element becomes visible using the default number of swipes.
     */
    public WebElement scrollToElement(By locator) {
        return scrollToElement(
                locator,
                DEFAULT_MAX_SWIPES
        );
    }

    /**
     * Scrolls down the screen until the element becomes visible or
     * the maximum number of swipes is reached.
     *
     * @param locator   The locator for the element to search
     * @param maxSwipes Maximum number of swipes
     * @return Visible element found on screen
     */
    public WebElement scrollToElement(
            By locator,
            int maxSwipes
    ) {
        validateLocator(locator);
        validateMaxAttempts(maxSwipes);

        for (int attempt = 0;
             attempt <= maxSwipes;
             attempt++) {

            Optional<WebElement> displayedElement =
                    findDisplayedElement(locator);

            if (displayedElement.isPresent()) {
                return displayedElement.get();
            }

            if (attempt == maxSwipes) {
                break;
            }

            scrollDown();
        }

        throw new NoSuchElementException(
                "iOS element could not be found after "
                        + maxSwipes
                        + " swipe attempts. Locator: "
                        + locator
        );
    }

    /**
     * Scrolls until the element is found by its exact text.
     */
    public WebElement scrollToText(String text) {
        return scrollToText(
                text,
                DEFAULT_MAX_SWIPES
        );
    }

    /**
     * Searches for the element by exact text using the specified
     * maximum number of swipes.
     */
    public WebElement scrollToText(
            String text,
            int maxSwipes
    ) {
        validateRequiredText(text);
        validateMaxAttempts(maxSwipes);

        String escapedText =
                escapeIOSPredicateValue(text);

        By locator =
                AppiumBy.iOSNsPredicateString(
                        "label == '"
                                + escapedText
                                + "' OR name == '"
                                + escapedText
                                + "' OR value == '"
                                + escapedText
                                + "'"
                );

        return scrollToElement(
                locator,
                maxSwipes
        );
    }

    /**
     * Scrolls until an element containing the text fragment is found.
     */
    public WebElement scrollToTextContains(String text) {
        return scrollToTextContains(
                text,
                DEFAULT_MAX_SWIPES
        );
    }

    /**
     * Searches for an element containing the text fragment using the
     * specified maximum number of swipes.
     */
    public WebElement scrollToTextContains(
            String text,
            int maxSwipes
    ) {
        validateRequiredText(text);
        validateMaxAttempts(maxSwipes);

        String escapedText =
                escapeIOSPredicateValue(text);

        By locator =
                AppiumBy.iOSNsPredicateString(
                        "label CONTAINS '"
                                + escapedText
                                + "' OR name CONTAINS '"
                                + escapedText
                                + "' OR value CONTAINS '"
                                + escapedText
                                + "'"
                );

        return scrollToElement(
                locator,
                maxSwipes
        );
    }

    /**
     * Attempts to reach the end of the page.
     *
     * Because iOS swipe does not return a Boolean indicating whether
     * more scrolling is possible, the page source is compared.
     */
    public void scrollToEnd() {
        scrollToEnd(DEFAULT_MAX_SWIPES);
    }

    /**
     * Scrolls down until the page source stops changing or the
     * maximum number of swipes is reached.
     */
    public void scrollToEnd(int maxSwipes) {
        validateMaxAttempts(maxSwipes);

        String previousPageSource =
                iosDriver.getPageSource();

        for (int i = 0; i < maxSwipes; i++) {
            scrollDown();

            String currentPageSource =
                    iosDriver.getPageSource();

            if (currentPageSource.equals(
                    previousPageSource
            )) {
                return;
            }

            previousPageSource =
                    currentPageSource;
        }
    }

    // -------------------- SWIPE --------------------

    /**
     * Performs a swipe on the entire screen.
     */
    public void swipeScreen(
            SwipeDirection direction
    ) {
        validateDirection(direction);

        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: swipe",
                        Map.of(
                                "direction",
                                direction.getValue()
                        )
                );
    }

    /**
     * Performs a swipe on the element found by the locator.
     */
    public void swipe(
            By locator,
            SwipeDirection direction
    ) {
        WebElement element =
                waitForVisibility(locator);

        swipe(element, direction);
    }

    /**
     * Performs a swipe on the given WebElement.
     */
    public void swipe(
            WebElement element,
            SwipeDirection direction
    ) {
        validateDirection(direction);

        WebElement visibleElement =
                waitForVisibility(element);

        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: swipe",
                        Map.of(
                                "elementId",
                                getElementId(visibleElement),
                                "direction",
                                direction.getValue()
                        )
                );
    }

    // -------------------- LONG PRESS --------------------

    /**
     * Performs a long press on the element using the default duration.
     */
    public void longPress(By locator) {
        longPress(
                locator,
                DEFAULT_LONG_PRESS_DURATION
        );
    }

    /**
     * Performs a long press on the element found by the locator using
     * the specified duration.
     */
    public void longPress(
            By locator,
            Duration duration
    ) {
        WebElement element =
                waitForVisibility(locator);

        longPress(element, duration);
    }

    /**
     * Performs a long press on the given WebElement using the
     * specified duration.
     */
    public void longPress(
            WebElement element,
            Duration duration
    ) {
        validateDuration(duration);

        WebElement visibleElement =
                waitForVisibility(element);

        double durationInSeconds =
                duration.toMillis() / 1000.0;

        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: touchAndHold",
                        Map.of(
                                "elementId",
                                getElementId(visibleElement),
                                "duration",
                                durationInSeconds
                        )
                );
    }

    // -------------------- DRAG AND DROP --------------------

    /**
     * Drags the source element found by the locator to the center of
     * the target element.
     */
    public void dragAndDrop(
            By sourceLocator,
            By targetLocator
    ) {
        WebElement source =
                waitForVisibility(sourceLocator);

        WebElement target =
                waitForVisibility(targetLocator);

        dragAndDrop(source, target);
    }

    /**
     * Drags the source WebElement to the center of the target WebElement.
     */
    public void dragAndDrop(
            WebElement source,
            WebElement target
    ) {
        WebElement visibleSource =
                waitForVisibility(source);

        WebElement visibleTarget =
                waitForVisibility(target);

        Point targetLocation =
                visibleTarget.getLocation();

        Dimension targetSize =
                visibleTarget.getSize();

        int targetCenterX =
                targetLocation.getX()
                        + targetSize.getWidth() / 2;

        int targetCenterY =
                targetLocation.getY()
                        + targetSize.getHeight() / 2;

        dragAndDrop(
                visibleSource,
                targetCenterX,
                targetCenterY
        );
    }

    /**
     * Drags the source element found by the locator to the given coordinates.
     */
    public void dragAndDrop(
            By sourceLocator,
            int endX,
            int endY
    ) {
        WebElement source =
                waitForVisibility(sourceLocator);

        dragAndDrop(source, endX, endY);
    }

    /**
     * Drags the WebElement to the given screen coordinates.
     */
    public void dragAndDrop(
            WebElement source,
            int endX,
            int endY
    ) {
        validateCoordinates(endX, endY);

        WebElement visibleSource =
                waitForVisibility(source);

        Point sourceLocation =
                visibleSource.getLocation();

        Dimension sourceSize =
                visibleSource.getSize();

        int sourceCenterX =
                sourceLocation.getX()
                        + sourceSize.getWidth() / 2;

        int sourceCenterY =
                sourceLocation.getY()
                        + sourceSize.getHeight() / 2;

        Map<String, Object> parameters =
                new HashMap<>();

        parameters.put(
                "duration",
                DEFAULT_DRAG_DURATION_SECONDS
        );

        parameters.put(
                "fromX",
                sourceCenterX
        );

        parameters.put(
                "fromY",
                sourceCenterY
        );

        parameters.put(
                "toX",
                endX
        );

        parameters.put(
                "toY",
                endY
        );

        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: dragFromToForDuration",
                        parameters
                );
    }

    // -------------------- IOS PICKER --------------------

    /**
     * Changes the picker wheel value toward the target value.
     */
    public void selectPickerValue(
            By pickerLocator,
            String targetValue,
            PickerDirection direction
    ) {
        WebElement picker =
                waitForVisibility(pickerLocator);

        selectPickerValue(
                picker,
                targetValue,
                direction
        );
    }

    /**
     * Changes the picker wheel value in the specified direction until
     * the target value is reached.
     */
    public void selectPickerValue(
            WebElement picker,
            String targetValue,
            PickerDirection direction
    ) {
        validateRequiredText(targetValue);
        validatePickerDirection(direction);

        WebElement visiblePicker =
                waitForVisibility(picker);

        Map<String, Object> parameters =
                new HashMap<>();

        parameters.put(
                "elementId",
                getElementId(visiblePicker)
        );

        parameters.put(
                "order",
                direction.getValue()
        );

        parameters.put(
                "offset",
                DEFAULT_PICKER_OFFSET
        );

        parameters.put(
                "value",
                targetValue
        );

        parameters.put(
                "maxAttempts",
                DEFAULT_PICKER_MAX_ATTEMPTS
        );

        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: selectPickerWheelValue",
                        parameters
                );
    }

    /**
     * Tries to set the picker value directly with sendKeys.
     *
     * This can be used if the app's picker structure supports it.
     */
    public void setPickerValueDirectly(
            By pickerLocator,
            String value
    ) {
        WebElement picker =
                waitForVisibility(pickerLocator);

        setPickerValueDirectly(
                picker,
                value
        );
    }

    public void setPickerValueDirectly(
            WebElement picker,
            String value
    ) {
        validateRequiredText(value);

        waitForVisibility(picker)
                .sendKeys(value);
    }

    /**
     * Returns the current value attribute of the picker.
     */
    public String getPickerValue(
            By pickerLocator
    ) {
        WebElement picker =
                waitForVisibility(pickerLocator);

        return getPickerValue(picker);
    }

    public String getPickerValue(
            WebElement picker
    ) {
        WebElement visiblePicker =
                waitForVisibility(picker);

        String value =
                visiblePicker.getAttribute("value");

        if (value != null && !value.isBlank()) {
            return value;
        }

        return visiblePicker.getText();
    }

    // -------------------- IOS SLIDER --------------------

    /**
     * Sets the slider value.
     *
     * The value must be between 0.0 and 1.0.
     */
    public void setSliderValue(
            By sliderLocator,
            double value
    ) {
        WebElement slider =
                waitForVisibility(sliderLocator);

        setSliderValue(slider, value);
    }

    public void setSliderValue(
            WebElement slider,
            double value
    ) {
        validateSliderValue(value);

        waitForVisibility(slider)
                .sendKeys(String.valueOf(value));
    }

    /**
     * Returns the current slider value as a number between 0.0 and 1.0.
     */
    public double getSliderValue(
            By sliderLocator
    ) {
        WebElement slider =
                waitForVisibility(sliderLocator);

        return getSliderValue(slider);
    }

    public double getSliderValue(
            WebElement slider
    ) {
        String value =
                waitForVisibility(slider)
                        .getAttribute("value");

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Slider value attribute is null or blank."
            );
        }

        String normalizedValue =
                value.replace("%", "")
                        .trim();

        try {
            double parsedValue =
                    Double.parseDouble(
                            normalizedValue
                    );

            /*
             * Some apps may return values like 50 or 50% instead of 0.5.
             */
            if (parsedValue > 1) {
                return parsedValue / 100.0;
            }

            return parsedValue;

        } catch (NumberFormatException exception) {
            throw new IllegalStateException(
                    "Slider value could not be converted to a number: "
                            + value,
                    exception
            );
        }
    }

    // -------------------- DEVICE ACTIONS --------------------

    /**
     * Returns to the device home screen by sending the app to the background.
     */
    public void pressHome() {
        ((JavascriptExecutor) iosDriver)
                .executeScript(
                        "mobile: pressButton",
                        Map.of(
                                "name",
                                "home"
                        )
                );
    }

    /**
     * iOS does not have a physical back button, so the app's navigation back button is clicked.
     */
    public void pressBack(By backButtonLocator) {
        click(backButtonLocator);
    }

    /**
     * Clicks the back button provided as a WebElement.
     */
    public void pressBack(WebElement backButton) {
        click(backButton);
    }

    // -------------------- KEYBOARD ACTIONS --------------------

    /**
     * Clicks an iOS keyboard button by its accessibility ID.
     *
     * Example values:
     * Done, Return, Search, Go, Next
     */
    public void pressKeyboardButton(
            String buttonName
    ) {
        validateRequiredText(buttonName);

        By keyboardButton =
                AppiumBy.accessibilityId(
                        buttonName
                );

        click(keyboardButton);
    }

    public void pressKeyboardDone() {
        pressKeyboardButton("Done");
    }

    public void pressKeyboardReturn() {
        pressKeyboardButton("Return");
    }

    public void pressKeyboardSearch() {
        pressKeyboardButton("Search");
    }

    public void pressKeyboardGo() {
        pressKeyboardButton("Go");
    }

    public void pressKeyboardNext() {
        pressKeyboardButton("Next");
    }

    /**
     * Tries to dismiss the iOS keyboard.
     *
     * Keyboard dismissal behavior may vary depending on the app and
     * keyboard type.
     */
    public void hideKeyboard() {
        try {
            iosDriver.hideKeyboard();

        } catch (WebDriverException firstException) {
            try {
                pressKeyboardDone();

            } catch (WebDriverException ignored) {
                // The keyboard may not be open, or the Done button may not exist.
            }
        }
    }

    // -------------------- INTERNAL VALIDATIONS --------------------

    private void validateDirection(
            SwipeDirection direction
    ) {
        if (direction == null) {
            throw new IllegalArgumentException(
                    "Swipe direction cannot be null."
            );
        }
    }

    private void validatePickerDirection(
            PickerDirection direction
    ) {
        if (direction == null) {
            throw new IllegalArgumentException(
                    "Picker direction cannot be null."
            );
        }
    }

    private void validateMaxAttempts(
            int maxAttempts
    ) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException(
                    "Maximum attempt count must be greater than zero."
            );
        }
    }

    private void validateDuration(
            Duration duration
    ) {
        if (duration == null
                || duration.isZero()
                || duration.isNegative()) {

            throw new IllegalArgumentException(
                    "Duration must be greater than zero."
            );
        }
    }

    private void validateCoordinates(
            int x,
            int y
    ) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                    "Coordinates cannot be negative."
            );
        }

        Dimension screenSize =
                iosDriver.manage()
                        .window()
                        .getSize();

        /*
         * Because coordinates start at 0, values equal to the width or
         * height are also outside the screen.
         */
        if (x >= screenSize.getWidth()
                || y >= screenSize.getHeight()) {

            throw new IllegalArgumentException(
                    "Coordinates are outside the screen. "
                            + "Screen size: "
                            + screenSize.getWidth()
                            + "x"
                            + screenSize.getHeight()
                            + ", given coordinates: "
                            + x
                            + ", "
                            + y
            );
        }
    }

    private void validateRequiredText(
            String text
    ) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(
                    "Text cannot be null or blank."
            );
        }
    }

    private void validateSliderValue(
            double value
    ) {
        if (Double.isNaN(value)
                || Double.isInfinite(value)
                || value < 0
                || value > 1) {

            throw new IllegalArgumentException(
                    "iOS slider value must be between 0.0 and 1.0."
            );
        }
    }

    private String escapeIOSPredicateValue(
            String value
    ) {
        return value
                .replace("\\", "\\\\")
                .replace("'", "\\'");
    }
}

