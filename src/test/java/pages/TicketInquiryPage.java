package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BaseActions;

import java.time.Duration;

public class TicketInquiryPage {

    private final BaseActions baseActions;

    // -------------------- NAVIGATION ELEMENTS --------------------

    @iOSXCUITFindBy(accessibility = "results_tab")
    @AndroidFindBy(accessibility = "results_tab")
    private WebElement resultsTab;

    @iOSXCUITFindBy(accessibility = "lotteries_button")
    @AndroidFindBy(accessibility = "lotteries_button")
    private WebElement lotteriesButton;

    // -------------------- TICKET INQUIRY ELEMENTS --------------------

    @iOSXCUITFindBy(accessibility = "ticket_serial_input")
    @AndroidFindBy(accessibility = "ticket_serial_input")
    private WebElement ticketSerialInput;

    @iOSXCUITFindBy(accessibility = "query_ticket_button")
    @AndroidFindBy(accessibility = "query_ticket_button")
    private WebElement queryTicketButton;

    // -------------------- RESULT ELEMENTS --------------------

    @iOSXCUITFindBy(accessibility = "winning_ticket_label")
    @AndroidFindBy(accessibility = "winning_ticket_label")
    private WebElement winningTicketLabel;

    @iOSXCUITFindBy(accessibility = "losing_ticket_label")
    @AndroidFindBy(accessibility = "losing_ticket_label")
    private WebElement losingTicketLabel;

    @iOSXCUITFindBy(accessibility = "invalid_ticket_label")
    @AndroidFindBy(accessibility = "invalid_ticket_label")
    private WebElement invalidTicketLabel;

    // -------------------- CONSTRUCTOR --------------------

    public TicketInquiryPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    // -------------------- PAGE ACTIONS --------------------

    /**
     * Navigates from the home screen to the Lotteries (Çekilişler) section
     * by tapping the Results tab first and then the Lotteries entry.
     */
    public void navigateToLotteryResults() {
        baseActions.click(resultsTab);
        baseActions.click(lotteriesButton);
    }

    /**
     * Enters the given serial number into the ticket inquiry field
     * and taps the query button to submit.
     *
     * @param serialNumber the ticket serial number to look up
     */
    public void inquireTicket(String serialNumber) {
        baseActions.clearAndSend(ticketSerialInput, serialNumber);
        baseActions.click(queryTicketButton);
    }

    /**
     * Verifies the correct result state is displayed based on the expected status text.
     * Supported values: "Kazandın!", "Kazanamadın!", "Geçersiz bilet!"
     *
     * @param statusText the expected ticket result status
     */
    public void checkTicketDetail(String statusText) {
        switch (statusText) {
            case "Kazandın!" -> {
                baseActions.waitForVisibility(winningTicketLabel);
                Assert.assertTrue(
                        baseActions.isDisplayed(winningTicketLabel),
                        "The details of the winning ticket were not displayed"
                );
            }
            case "Kazanamadın!" -> {
                baseActions.waitForVisibility(losingTicketLabel);
                Assert.assertTrue(
                        baseActions.isDisplayed(losingTicketLabel),
                        "Details of the losing ticket were not displayed"
                );
            }
            case "Geçersiz bilet!" -> {
                baseActions.waitForVisibility(invalidTicketLabel);
                Assert.assertTrue(
                        baseActions.isDisplayed(invalidTicketLabel),
                        "Details of the invalid ticket were not displayed"
                );
            }
            default -> throw new IllegalArgumentException(
                    "Unrecognised ticket status text: \"" + statusText + "\". " +
                    "Expected one of: Kazandın!, Kazanamadın!, Geçersiz bilet!"
            );
        }
    }
}
