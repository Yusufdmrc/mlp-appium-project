package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BaseActions;
import utils.LoginHelper;

import java.time.Duration;

public class PasswordUpdatePage {

    private final BaseActions baseActions;

    // -------------------- PASSWORD UPDATE FORM --------------------

    @iOSXCUITFindBy(accessibility = "new_password_input")
    @AndroidFindBy(accessibility = "new_password_input")
    private WebElement newPasswordInput;

    @iOSXCUITFindBy(accessibility = "repeat_password_input")
    @AndroidFindBy(accessibility = "repeat_password_input")
    private WebElement repeatPasswordInput;

    @iOSXCUITFindBy(accessibility = "save_password_button")
    @AndroidFindBy(accessibility = "save_password_button")
    private WebElement savePasswordButton;

    // -------------------- POST-UPDATE NAVIGATION --------------------

    @iOSXCUITFindBy(accessibility = "navigate_login_button")
    @AndroidFindBy(accessibility = "navigate_login_button")
    private WebElement navigateLoginButton;

    // -------------------- SUCCESS INDICATOR --------------------

    @iOSXCUITFindBy(accessibility = "password_update_success_label")
    @AndroidFindBy(accessibility = "password_update_success_label")
    private WebElement passwordUpdateSuccessLabel;

    // -------------------- CONSTRUCTOR --------------------

    public PasswordUpdatePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    // -------------------- PUBLIC ACTIONS --------------------

    /**
     * Enters the new password into both the "new password" and "confirm password"
     * fields, then taps the Save button to submit.
     *
     * @param password the new password to set
     */
    public void changeThePasswordAndSubmit(String password) {
        baseActions.clearAndSend(newPasswordInput, password);
        baseActions.clearAndSend(repeatPasswordInput, password);
        baseActions.click(savePasswordButton);
    }

    /**
     * Taps the button that navigates back to the Login screen after
     * a successful password update.
     */
    public void navigateToLoginPage() {
        baseActions.click(navigateLoginButton);
    }
}
