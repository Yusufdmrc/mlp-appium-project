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
import utils.LoginHelper;

import java.time.Duration;

public class PasswordRetrievalPage {

    private final BaseActions baseActions;

    // -------------------- NAVIGATION --------------------

    @iOSXCUITFindBy(accessibility = "forgot_password_link")
    @AndroidFindBy(accessibility = "forgot_password_link")
    private WebElement forgotPasswordLink;

    // -------------------- FORM FIELDS --------------------

    @iOSXCUITFindBy(accessibility = "tc_id_input")
    @AndroidFindBy(accessibility = "tc_id_input")
    private WebElement tcIdInput;

    @iOSXCUITFindBy(accessibility = "birthdate_input")
    @AndroidFindBy(accessibility = "birthdate_input")
    private WebElement birthdateInput;

    @iOSXCUITFindBy(accessibility = "email_input")
    @AndroidFindBy(accessibility = "email_input")
    private WebElement emailInput;

    @iOSXCUITFindBy(accessibility = "continue_button")
    @AndroidFindBy(accessibility = "continue_button")
    private WebElement continueButton;

    // -------------------- OTP FIELDS --------------------

    @iOSXCUITFindBy(accessibility = "otp_input")
    @AndroidFindBy(accessibility = "otp_input")
    private WebElement otpInput;

    @iOSXCUITFindBy(accessibility = "submit_otp_button")
    @AndroidFindBy(accessibility = "submit_otp_button")
    private WebElement submitOtpButton;

    // -------------------- CONSTRUCTOR --------------------

    public PasswordRetrievalPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    // -------------------- NAVIGATION --------------------

    /**
     * Taps the "Forgot Password" link on the login screen to open
     * the Password Retrieval page.
     */
    public void navigateToPasswordRetrievalPage() {
        baseActions.click(forgotPasswordLink);
    }

    // -------------------- FORM INTERACTIONS --------------------

    private void enterTcId(String tcIdKey) {
        baseActions.clearAndSend(tcIdInput, LoginHelper.getUserName(tcIdKey));
    }

    private void enterBirthdate(String birthdate) {
        // "empty" is handled by LoginHelper returning "" — field left blank
        String value = LoginHelper.getUserName(birthdate);
        if (!value.isEmpty()) {
            baseActions.clearAndSend(birthdateInput, value);
        }
    }

    private void enterEmail(String emailKey) {
        baseActions.clearAndSend(emailInput, LoginHelper.getUserName(emailKey));
    }

    private void clickContinueButton() {
        baseActions.click(continueButton);
    }

    // -------------------- PUBLIC ACTIONS --------------------

    /**
     * Fills each field that is not "empty", then asserts that the Continue
     * button remains disabled — validating that the form cannot be submitted
     * with missing required data.
     *
     * @param tcId      TC ID key (e.g. "correctTcID" or "empty")
     * @param birthDate birthdate string (e.g. "01122001" or "empty")
     * @param email     email key (e.g. "correctEmail" or "empty")
     */
    public void verifyPasswordRetrievalFormNotSubmittable(
            String tcId,
            String birthDate,
            String email
    ) {
        enterTcId(tcId);
        enterBirthdate(birthDate);
        enterEmail(email);

        Assert.assertFalse(
                continueButton.isEnabled(),
                "Continue button should be disabled when required fields are missing. " +
                "tcId=\"" + tcId + "\", birthDate=\"" + birthDate + "\", email=\"" + email + "\""
        );
    }

    /**
     * Fills all three fields and taps Continue to proceed to the OTP screen.
     *
     * @param tcId      TC ID key
     * @param birthDate birthdate string (ddMMyyyy)
     * @param email     email key
     */
    public void fillPasswordRetrievalForm(
            String tcId,
            String birthDate,
            String email
    ) {
        enterTcId(tcId);
        enterBirthdate(birthDate);
        enterEmail(email);
        clickContinueButton();
    }

    /**
     * Enters the OTP from config and submits it.
     */
    public void enterAndSubmitOtp() {
        baseActions.clearAndSend(otpInput, ConfigReader.get("otp.number"));
        baseActions.click(submitOtpButton);
    }
}
