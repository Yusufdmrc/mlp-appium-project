package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public class AndroidActions extends BaseActions {

    private static final int DEFAULT_MAX_SCROLLS = 10;
    private static final double DEFAULT_SCROLL_PERCENT = 0.80;
    private static final double DEFAULT_SWIPE_PERCENT = 0.75;
    private static final Duration DEFAULT_LONG_PRESS_DURATION =
            Duration.ofSeconds(2);

    private final AndroidDriver  androidDriver;

    public AndroidActions(AndroidDriver driver) {
        super(driver);
        this.androidDriver = driver;
    }

    // -------------------- SCROLL --------------------

    /**
     * Scrolls the content downward.
     *
     * @return true if more scrolling is possible.
     */
    public boolean scrollDown() {
        return scroll(
                SwipeDirection.DOWN,
                DEFAULT_SCROLL_PERCENT
        );
    }

    /**
     * Scrolls the content upward.
     *
     * @return true if more scrolling is possible.
     */
    public boolean scrollUp() {
        return scroll(
                SwipeDirection.UP,
                DEFAULT_SCROLL_PERCENT
        );
    }

    /**
     * Performs a scroll gesture on the screen in the specified direction.
     */
    public boolean scroll(
            SwipeDirection direction,
            double percent
    ) {
        validateDirection(direction);
        validatePercent(percent);

        Dimension screenSize =
                androidDriver.manage()
                        .window()
                        .getSize();

        int left =
                (int) (screenSize.getWidth() * 0.10);

        int top =
                (int) (screenSize.getHeight() * 0.20);

        int width =
                (int) (screenSize.getWidth() * 0.80);

        int height =
                (int) (screenSize.getHeight() * 0.60);

        Object result =
                ((JavascriptExecutor) androidDriver)
                        .executeScript(
                                "mobile: scrollGesture",
                                Map.of(
                                        "left", left,
                                        "top", top,
                                        "width", width,
                                        "height", height,
                                        "direction",
                                        direction.getValue(),
                                        "percent", percent
                                )
                        );

        return result instanceof Boolean
                && (Boolean) result;
    }

    /**
     * Scrolls down until the element becomes visible.
     */
    public WebElement scrollToElement(By locator) {
        return scrollToElement(
                locator,
                DEFAULT_MAX_SCROLLS
        );
    }

    /**
     * Scrolls until the element becomes visible, the end is reached,
     * or the maximum number of scroll attempts is reached.
     */
    public WebElement scrollToElement(
            By locator,
            int maxScrolls
    ) {
        validateLocator(locator);
        validateMaxAttempts(maxScrolls);

        for (int attempt = 0;
             attempt <= maxScrolls;
             attempt++) {

            Optional<WebElement> displayedElement =
                    findDisplayedElement(locator);

            if (displayedElement.isPresent()) {
                return displayedElement.get();
            }

            if (attempt == maxScrolls) {
                break;
            }

            boolean canScrollMore =
                    scrollDown();

            if (!canScrollMore) {
                break;
            }
        }

        throw new NoSuchElementException(
                "Android element could not be found after "
                        + maxScrolls
                        + " scroll attempts. Locator: "
                        + locator
        );
    }

    /**
     * Finds an element by exact text using Android UiScrollable.
     */
    public WebElement scrollToText(String text) {
        validateRequiredText(text);

        String escapedText =
                escapeAndroidUiSelectorText(text);

        By locator =
                AppiumBy.androidUIAutomator(
                        "new UiScrollable("
                                + "new UiSelector().scrollable(true)"
                                + ").scrollIntoView("
                                + "new UiSelector().text(\""
                                + escapedText
                                + "\"));"
                );

        return waitForVisibility(locator);
    }

    /**
     * Finds an element containing part of the text using Android UiScrollable.
     */
    public WebElement scrollToTextContains(
            String text
    ) {
        validateRequiredText(text);

        String escapedText =
                escapeAndroidUiSelectorText(text);

        By locator =
                AppiumBy.androidUIAutomator(
                        "new UiScrollable("
                                + "new UiSelector().scrollable(true)"
                                + ").scrollIntoView("
                                + "new UiSelector().textContains(\""
                                + escapedText
                                + "\"));"
                );

        return waitForVisibility(locator);
    }

    /**
     * Scrolls to the end using the default maximum number of scroll attempts.
     */
    public void scrollToEnd() {
        scrollToEnd(DEFAULT_MAX_SCROLLS);
    }

    /**
     * Scrolls down until no more scrolling is possible or the maximum
     * number of scroll attempts is reached.
     */
    public void scrollToEnd(int maxScrolls) {
        validateMaxAttempts(maxScrolls);

        for (int i = 0; i < maxScrolls; i++) {
            boolean canScrollMore =
                    scrollDown();

            if (!canScrollMore) {
                return;
            }
        }
    }

    // -------------------- SWIPE --------------------

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

        ((JavascriptExecutor) androidDriver)
                .executeScript(
                        "mobile: swipeGesture",
                        Map.of(
                                "elementId",
                                getElementId(visibleElement),
                                "direction",
                                direction.getValue(),
                                "percent",
                                DEFAULT_SWIPE_PERCENT
                        )
                );
    }

    /**
     * Performs a swipe on the entire screen using the default percent.
     */
    public void swipeScreen(
            SwipeDirection direction
    ) {
        swipeScreen(
                direction,
                DEFAULT_SWIPE_PERCENT
        );
    }

    /**
     * Performs a swipe on the entire screen using the specified percent.
     */
    public void swipeScreen(
            SwipeDirection direction,
            double percent
    ) {
        validateDirection(direction);
        validatePercent(percent);

        Dimension screenSize =
                androidDriver.manage()
                        .window()
                        .getSize();

        int left =
                (int) (screenSize.getWidth() * 0.10);

        int top =
                (int) (screenSize.getHeight() * 0.20);

        int width =
                (int) (screenSize.getWidth() * 0.80);

        int height =
                (int) (screenSize.getHeight() * 0.60);

        ((JavascriptExecutor) androidDriver)
                .executeScript(
                        "mobile: swipeGesture",
                        Map.of(
                                "left", left,
                                "top", top,
                                "width", width,
                                "height", height,
                                "direction",
                                direction.getValue(),
                                "percent", percent
                        )
                );
    }

    // -------------------- LONG PRESS --------------------

    public void longPress(By locator) {
        longPress(
                locator,
                DEFAULT_LONG_PRESS_DURATION
        );
    }

    public void longPress(
            By locator,
            Duration duration
    ) {
        WebElement element =
                waitForVisibility(locator);

        longPress(element, duration);
    }

    public void longPress(
            WebElement element,
            Duration duration
    ) {
        validateDuration(duration);

        WebElement visibleElement =
                waitForVisibility(element);

        ((JavascriptExecutor) androidDriver)
                .executeScript(
                        "mobile: longClickGesture",
                        Map.of(
                                "elementId",
                                getElementId(visibleElement),
                                "duration",
                                duration.toMillis()
                        )
                );
    }

    // -------------------- DRAG AND DROP --------------------

    /**
     * Drags the source element to the center of the target element.
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
     * Drags the element found by the locator to the given coordinates.
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

        ((JavascriptExecutor) androidDriver)
                .executeScript(
                        "mobile: dragGesture",
                        Map.of(
                                "elementId",
                                getElementId(visibleSource),
                                "endX", endX,
                                "endY", endY
                        )
                );
    }

    // -------------------- DEVICE KEYS --------------------

    public void pressBack() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.BACK)
        );
    }

    public void pressHome() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.HOME)
        );
    }

    public void pressEnter() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.ENTER)
        );
    }

    public void pressSearch() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.SEARCH)
        );
    }

    public void pressTab() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.TAB)
        );
    }

    /**
     * Dismisses the Android keyboard.
     *
     * A WebDriverException is caught so the test does not fail when
     * the keyboard is already closed.
     */
    public void hideKeyboard() {
        try {
            androidDriver.hideKeyboard();

        } catch (WebDriverException ignored) {
            // The keyboard may already be closed.
        }
    }

    // -------------------- KEYBOARD ACTION --------------------

    /**
     * Executes the IME action on the Android keyboard.
     *
     * Example values:
     * done, search, go, next, previous, send
     */
    public void performEditorAction(String action) {
        if (action == null || action.isBlank()) {
            throw new IllegalArgumentException(
                    "Editor action cannot be null or blank."
            );
        }

        ((JavascriptExecutor) androidDriver)
                .executeScript(
                        "mobile: performEditorAction",
                        Map.of(
                                "action",
                                action.trim()
                                        .toLowerCase()
                        )
                );
    }

    public void pressKeyboardDone() {
        performEditorAction("done");
    }

    public void pressKeyboardSearch() {
        performEditorAction("search");
    }

    public void pressKeyboardNext() {
        performEditorAction("next");
    }

    public void pressKeyboardGo() {
        performEditorAction("go");
    }

    public void pressKeyboardSend() {
        performEditorAction("send");
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

    private void validatePercent(double percent) {
        if (percent <= 0 || percent > 1) {
            throw new IllegalArgumentException(
                    "Gesture percent must be greater than 0 "
                            + "and less than or equal to 1."
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
                androidDriver.manage()
                        .window()
                        .getSize();

        /*
         * Because screen coordinates start at 0, a coordinate equal to
         * the width or height is also outside the screen.
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

    private String escapeAndroidUiSelectorText(
            String value
    ) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
