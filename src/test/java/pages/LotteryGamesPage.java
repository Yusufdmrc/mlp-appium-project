package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BaseActions;
import utils.ConfigReader;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LotteryGamesPage {

    private final BaseActions baseActions;

    // -------------------- HOME PAGE NAVIGATION --------------------

    @iOSXCUITFindBy(accessibility = "sayisal_loto_game")
    @AndroidFindBy(accessibility = "sayisal_loto_game")
    private WebElement sayisalLotoGameButton;

    @iOSXCUITFindBy(accessibility = "super_loto_game")
    @AndroidFindBy(accessibility = "super_loto_game")
    private WebElement superLotoGameButton;

    @iOSXCUITFindBy(accessibility = "sans_topu_game")
    @AndroidFindBy(accessibility = "sans_topu_game")
    private WebElement sansTopuGameButton;

    @iOSXCUITFindBy(accessibility = "on_numara_game")
    @AndroidFindBy(accessibility = "on_numara_game")
    private WebElement onNumaraGameButton;

    // -------------------- GAME TYPE TABS --------------------

    @iOSXCUITFindBy(accessibility = "simple_tab")
    @AndroidFindBy(accessibility = "simple_tab")
    private WebElement simpleTab;

    @iOSXCUITFindBy(accessibility = "multiple_tab")
    @AndroidFindBy(accessibility = "multiple_tab")
    private WebElement multipleTab;

    // -------------------- TICKET PURCHASE --------------------

    @iOSXCUITFindBy(accessibility = "random_button")
    @AndroidFindBy(accessibility = "random_button")
    private WebElement randomButton;

    @iOSXCUITFindBy(accessibility = "buy_button")
    @AndroidFindBy(accessibility = "buy_button")
    private WebElement buyButton;

    @iOSXCUITFindBy(accessibility = "confirm_button")
    @AndroidFindBy(accessibility = "confirm_button")
    private WebElement confirmButton;

    @iOSXCUITFindBy(accessibility = "play_again_button")
    @AndroidFindBy(accessibility = "play_again_button")
    private WebElement playAgainButton;

    // -------------------- RECEIPT ELEMENTS --------------------

    @iOSXCUITFindBy(accessibility = "receipt_ticket_detail_title")
    @AndroidFindBy(accessibility = "receipt_ticket_detail_title")
    private WebElement receiptTicketDetailTitle;

    @iOSXCUITFindBy(accessibility = "receipt_date_text")
    @AndroidFindBy(accessibility = "receipt_date_text")
    private WebElement receiptDateText;

    @iOSXCUITFindBy(accessibility = "receipt_price_text")
    @AndroidFindBy(accessibility = "receipt_price_text")
    private WebElement receiptPriceText;

    @iOSXCUITFindBy(accessibility = "receipt_waiting_for_draw_title")
    @AndroidFindBy(accessibility = "receipt_waiting_for_draw_title")
    private WebElement receiptWaitingForDrawTitle;

    @iOSXCUITFindBy(accessibility = "receipt_draw_no_title")
    @AndroidFindBy(accessibility = "receipt_draw_no_title")
    private WebElement receiptDrawNoTitle;

    @iOSXCUITFindBy(accessibility = "receipt_hour_title")
    @AndroidFindBy(accessibility = "receipt_hour_title")
    private WebElement receiptHourTitle;

    @iOSXCUITFindBy(accessibility = "receipt_minute_title")
    @AndroidFindBy(accessibility = "receipt_minute_title")
    private WebElement receiptMinuteTitle;

    @iOSXCUITFindBy(accessibility = "receipt_date_title")
    @AndroidFindBy(accessibility = "receipt_date_title")
    private WebElement receiptDateTitle;

    @iOSXCUITFindBy(accessibility = "receipt_replay_button")
    @AndroidFindBy(accessibility = "receipt_replay_button")
    private WebElement receiptReplayButton;

    @iOSXCUITFindBy(accessibility = "receipt_detail_title")
    @AndroidFindBy(accessibility = "receipt_detail_title")
    private WebElement receiptDetailTitle;

    // -------------------- RECEIPT DETAIL FIELDS --------------------

    @iOSXCUITFindBy(accessibility = "receipt_detail_game_type")
    @AndroidFindBy(accessibility = "receipt_detail_game_type")
    private WebElement receiptDetailGameType;

    @iOSXCUITFindBy(accessibility = "receipt_detail_game_code")
    @AndroidFindBy(accessibility = "receipt_detail_game_code")
    private WebElement receiptDetailGameCode;

    @iOSXCUITFindBy(accessibility = "receipt_detail_date")
    @AndroidFindBy(accessibility = "receipt_detail_date")
    private WebElement receiptDetailDate;

    @iOSXCUITFindBy(accessibility = "receipt_detail_account")
    @AndroidFindBy(accessibility = "receipt_detail_account")
    private WebElement receiptDetailAccount;

    @iOSXCUITFindBy(accessibility = "receipt_detail_serial_no")
    @AndroidFindBy(accessibility = "receipt_detail_serial_no")
    private WebElement receiptDetailSerialNo;

    @iOSXCUITFindBy(accessibility = "receipt_detail_column_count")
    @AndroidFindBy(accessibility = "receipt_detail_column_count")
    private WebElement receiptDetailColumnCount;

    @iOSXCUITFindBy(accessibility = "receipt_detail_game_no")
    @AndroidFindBy(accessibility = "receipt_detail_game_no")
    private WebElement receiptDetailGameNo;

    // -------------------- TICKET DETAIL (POST-PURCHASE) --------------------

    @iOSXCUITFindBy(accessibility = "ticket_detail_game_code")
    @AndroidFindBy(accessibility = "ticket_detail_game_code")
    private WebElement ticketDetailGameCode;

    @iOSXCUITFindBy(accessibility = "ticket_detail_game_no")
    @AndroidFindBy(accessibility = "ticket_detail_game_no")
    private WebElement ticketDetailGameNo;

    @iOSXCUITFindBy(accessibility = "ticket_detail_serial_no")
    @AndroidFindBy(accessibility = "ticket_detail_serial_no")
    private WebElement ticketDetailSerialNo;

    @iOSXCUITFindBy(accessibility = "ticket_detail_column_count")
    @AndroidFindBy(accessibility = "ticket_detail_column_count")
    private WebElement ticketDetailColumnCount;

    @iOSXCUITFindBy(accessibility = "ticket_detail_date")
    @AndroidFindBy(accessibility = "ticket_detail_date")
    private WebElement ticketDetailDate;

    @iOSXCUITFindBy(accessibility = "ticket_detail_account_no")
    @AndroidFindBy(accessibility = "ticket_detail_account_no")
    private WebElement ticketDetailAccountNo;

    // -------------------- CONSTRUCTOR --------------------

    public LotteryGamesPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    // -------------------- NAVIGATION --------------------

    /**
     * Navigates to the given lottery game page from the home screen.
     * Supported game names: "Sayisal Loto", "Super Loto", "Sans Topu", "On Numara"
     *
     * @param gameName display name of the game
     */
    public void navigateToLotteryGame(String gameName) {
        WebElement gameButton = resolveGameButton(gameName);
        baseActions.click(gameButton);
    }

    private WebElement resolveGameButton(String gameName) {
        switch (gameName) {
            case "Sayisal Loto" -> { return sayisalLotoGameButton; }
            case "Super Loto"   -> { return superLotoGameButton; }
            case "Sans Topu"    -> { return sansTopuGameButton; }
            case "On Numara"    -> { return onNumaraGameButton; }
            default -> throw new IllegalArgumentException(
                    "Unsupported game name: \"" + gameName + "\". " +
                    "Expected one of: Sayisal Loto, Super Loto, Sans Topu, On Numara"
            );
        }
    }

    // -------------------- GAME TYPE TAB --------------------

    /**
     * Navigates to the given ticket type tab within a game.
     * Supported values: "simple", "multiple"
     *
     * @param gameType tab identifier
     */
    public void navigateToGameType(String gameType) {
        switch (gameType) {
            case "simple"   -> baseActions.click(simpleTab);
            case "multiple" -> baseActions.click(multipleTab);
            default -> throw new IllegalArgumentException(
                    "Unsupported game type: \"" + gameType + "\". " +
                    "Expected one of: simple, multiple"
            );
        }
    }

    // -------------------- ROW ENTRY --------------------

    /**
     * Taps the random fill button the given number of times to populate draw rows.
     *
     * @param clickCount number of rows to fill
     */
    public void enterRowByRandomButton(int clickCount) {
        if (clickCount <= 0) {
            throw new IllegalArgumentException(
                    "Click count must be greater than zero, but was: " + clickCount
            );
        }
        for (int i = 0; i < clickCount; i++) {
            baseActions.click(randomButton);
        }
    }

    // -------------------- TICKET PURCHASE --------------------

    /**
     * Attempts to buy the ticket. Retries up to 5 times to handle loading states
     * and confirmation dialogs (e.g., the ">5000 TL" popup).
     * Asserts that the purchase completes successfully.
     */
    public void buyTicket() {
        boolean ticketBoughtSuccessfully = false;
        int attempts = 0;

        while (attempts < 5) {
            if (baseActions.isDisplayed(buyButton)) {
                baseActions.click(buyButton);
            }
            if (baseActions.isDisplayed(confirmButton)) {
                baseActions.click(confirmButton);
            }
            if (baseActions.isDisplayed(playAgainButton)) {
                ticketBoughtSuccessfully = true;
                break;
            }
            attempts++;
        }

        Assert.assertTrue(
                ticketBoughtSuccessfully,
                "Ticket could not be purchased after " + attempts + " attempts"
        );
    }

    // -------------------- RECEIPT VERIFICATION --------------------

    /**
     * Verifies all visible receipt elements after a successful ticket purchase.
     *
     * @param drawNumber     number of draws entered
     * @param columnNumber   number of columns played
     * @param standartNumber number of standard numbers per row
     * @param superstarNumber number of superstar numbers per row
     */
    public void verifyReceiptElements(
            int drawNumber,
            int columnNumber,
            int standartNumber,
            int superstarNumber
    ) {
        verifyReceiptTitleBox(drawNumber, columnNumber);
        verifyReceiptInfoBox();
        verifyReceiptDetailBox(columnNumber);
    }

    private void verifyReceiptTitleBox(int drawNumber, int columnNumber) {
        baseActions.waitForVisibility(receiptTicketDetailTitle);
        baseActions.waitForVisibility(receiptDateText);

        String dateText = baseActions.getText(receiptDateText);
        Assert.assertTrue(
                dateText.contains(getFormattedDate()),
                "Receipt date text does not contain expected date. Actual: " + dateText
        );
        verifyTimePart(dateText);

        baseActions.waitForVisibility(receiptPriceText);
    }

    private void verifyReceiptInfoBox() {
        baseActions.waitForVisibility(receiptWaitingForDrawTitle);
        baseActions.waitForVisibility(receiptDrawNoTitle);
        baseActions.waitForVisibility(receiptHourTitle);
        baseActions.waitForVisibility(receiptMinuteTitle);

        String nextDrawDateText = baseActions.getText(receiptDateTitle);
        verifyNextDrawDateIsInFuture(nextDrawDateText);
    }

    private void verifyReceiptDetailBox(int columnNumber) {
        baseActions.waitForVisibility(receiptDetailTitle);
        baseActions.waitForVisibility(receiptDetailGameType);
        baseActions.waitForVisibility(receiptDetailGameCode);

        // Date field should contain today's date
        String detailDateText = baseActions.getText(receiptDetailDate);
        Assert.assertTrue(
                detailDateText.contains(getFormattedDate()),
                "Receipt detail date does not contain expected date. Actual: " + detailDateText
        );

        // Account number field should match config
        String detailAccountText = baseActions.getText(receiptDetailAccount);
        Assert.assertTrue(
                detailAccountText.contains(ConfigReader.get("account.no")),
                "Receipt detail account number is incorrect. Actual: " + detailAccountText
        );

        // Serial number format: 27 alphanumeric chars, starts with current year's last two digits
        String serialNoText = baseActions.getText(receiptDetailSerialNo);
        verifySerialNoFormat(serialNoText);

        // Column count field should match the played column number
        String columnText = baseActions.getText(receiptDetailColumnCount);
        Assert.assertTrue(
                columnText.contains(columnNumber + " Kolon"),
                "Receipt column count is incorrect. Actual: " + columnText
        );

        baseActions.waitForVisibility(receiptDetailGameNo);
        baseActions.waitForVisibility(receiptReplayButton);
    }

    // -------------------- TICKET DETAILS VERIFICATION --------------------

    /**
     * Verifies the ticket detail screen fields displayed after purchase.
     * Fields verified: game code, game number, serial number, column count,
     * date, and account number — matching values shown on-screen.
     *
     * @param gameName the game whose detail screen is being verified
     */
    public void verifyDetailsOfTicket(String gameName) {
        String gameCode      = baseActions.getText(ticketDetailGameCode);
        String gameNo        = baseActions.getText(ticketDetailGameNo);
        String serialNo      = baseActions.getText(ticketDetailSerialNo);
        String columnCount   = baseActions.getText(ticketDetailColumnCount);
        String dateText      = baseActions.getText(ticketDetailDate);
        String accountNo     = baseActions.getText(ticketDetailAccountNo);

        Assert.assertNotNull(gameCode,    "Ticket detail game code is null for game: " + gameName);
        Assert.assertNotNull(gameNo,      "Ticket detail game number is null for game: " + gameName);
        Assert.assertNotNull(serialNo,    "Ticket detail serial number is null for game: " + gameName);
        Assert.assertNotNull(columnCount, "Ticket detail column count is null for game: " + gameName);

        Assert.assertTrue(
                dateText.contains(getFormattedDate()),
                "Ticket detail date does not contain expected date. Actual: " + dateText
        );
        Assert.assertTrue(
                accountNo.contains(ConfigReader.get("account.no")),
                "Ticket detail account number is incorrect. Actual: " + accountNo
        );

        verifySerialNoFormat(serialNo);
    }

    // -------------------- PRIVATE HELPERS --------------------

    /**
     * Returns today's date formatted as "d MMMM yyyy" in Turkish locale.
     * Example: "7 Kasım 2026"
     */
    private String getFormattedDate() {
        return LocalDate.now()
                .format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("tr", "TR")));
    }

    /**
     * Asserts that the time extracted from the given text (format "HH:mm")
     * falls within ±1 minute of the current system time.
     *
     * @param text text that contains a time segment after " - "
     */
    private void verifyTimePart(String text) {
        String[] parts = text.split("-");
        if (parts.length < 2) {
            throw new AssertionError(
                    "Could not extract time from text: " + text
            );
        }

        String timePart = parts[parts.length - 1].trim().substring(0, 5); // "HH:mm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime extractedTime = LocalTime.parse(timePart, timeFormatter);

        LocalTime now = LocalTime.now(ZoneId.systemDefault());
        LocalTime lowerBound = now.minusMinutes(1).withSecond(0).withNano(0);
        LocalTime upperBound = now.plusMinutes(1).withSecond(59).withNano(999_999_999);

        boolean withinRange =
                !extractedTime.isBefore(lowerBound) && !extractedTime.isAfter(upperBound);

        Assert.assertTrue(
                withinRange,
                "Receipt time \"" + extractedTime + "\" is not within ±1 minute of current time " + now
        );
    }

    /**
     * Asserts that the given date-time text represents a future point in time.
     * Expected format: "dd.MM.yyyy HH:mm"
     *
     * @param text date-time text from the next draw info box
     */
    private void verifyNextDrawDateIsInFuture(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            ZonedDateTime nextDraw = ZonedDateTime.parse(text.trim(), formatter.withZone(ZoneId.systemDefault()));
            Assert.assertTrue(
                    nextDraw.isAfter(ZonedDateTime.now()),
                    "Next draw date-time is not in the future: " + text
            );
        } catch (Exception e) {
            throw new AssertionError("Could not parse next draw date-time: \"" + text + "\"", e);
        }
    }

    /**
     * Validates the serial number format:
     * - Exactly 27 alphanumeric characters
     * - Starts with the last two digits of the current year
     *
     * @param text full text of the serial number field (may contain label + value)
     */
    private void verifySerialNoFormat(String text) {
        // Extract the last token which is the serial number itself
        String serialNo = text.trim().contains("\n")
                ? text.trim().split("\n")[text.trim().split("\n").length - 1].trim()
                : text.trim();

        Assert.assertEquals(
                serialNo.length(), 27,
                "Serial number length is not 27. Actual serial: " + serialNo
        );

        String currentYearSuffix = String.valueOf(Year.now().getValue()).substring(2);
        Assert.assertTrue(
                serialNo.startsWith(currentYearSuffix),
                "Serial number does not start with current year (" + currentYearSuffix + "): " + serialNo
        );

        Assert.assertTrue(
                serialNo.matches("[a-zA-Z0-9]+"),
                "Serial number contains non-alphanumeric characters: " + serialNo
        );
    }
}
