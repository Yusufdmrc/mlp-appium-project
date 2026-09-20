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

public class LoginPage {

    private final BaseActions baseActions;

    @iOSXCUITFindBy(accessibility = "login_button")
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"tr.sisal.millipiyango.test:id/\" and @text=\"Üye Girişi\"]")
    WebElement memberLoginButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='GİRİŞ YAP']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"GİRİŞ YAP\"]")
    WebElement loginButton;

    @iOSXCUITFindBy(accessibility = "Enter your email, TC number or account number")
    @AndroidFindBy(xpath = "//android.view.View[@text=\"E-posta / TC Numarası / Hesap numarası\"]")
    WebElement usernameBox;

    @iOSXCUITFindBy(accessibility = "Enter your password")
    @AndroidFindBy(id = "password")
    WebElement passwordBox;

    @iOSXCUITFindBy(accessibility = "modal close button")
    @AndroidFindBy(className = "android.widget.ImageButton")
    WebElement modalCloseButton;

    @iOSXCUITFindBy(accessibility = "balance_label")
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"tr.sisal.millipiyango.test:id/\" and @text=\"612.454.889,17 ₺\"]")
    WebElement balance;

    @iOSXCUITFindBy(accessibility = "error_message")
    @AndroidFindBy(className = "android.widget.TextView")
    WebElement errorMessage;

    @iOSXCUITFindBy(accessibility = "error_message")
    @AndroidFindBy(className = "android.widget.TextView")
    WebElement otherErrorMessage;

    public LoginPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }


    public void verifyMemberLoginButtonIsVisible() {
        baseActions.waitForVisibility(memberLoginButton);
    }

    public void clickMemberLoginButton() {
        baseActions.click(memberLoginButton);
    }

    public void writeUsernameForUsernameField(String username) {
        baseActions.clearAndSend(usernameBox, username);
    }

    public void writePasswordForPasswordField(String password) {
        baseActions.clearAndSend(passwordBox, password);
    }

    public void clickLogin() {
        baseActions.click(loginButton);
    }

    public void checkSuccessful() {
        baseActions.click(modalCloseButton);
        baseActions.waitForVisibility(balance);
    }

    public void checkUnsuccessfulLogin() {
        baseActions.waitForVisibility(balance);
    }

    public void checkErrorMessage(String expectedMessage) {
        String actualMessage = "";
        if (baseActions.isDisplayed(errorMessage)) {
            actualMessage = errorMessage.getText();
        } else if (baseActions.isDisplayed(otherErrorMessage)) {
            actualMessage = otherErrorMessage.getText();
        }
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public void login(String username, String password) {
        clickMemberLoginButton();
        writeUsernameForUsernameField(username);
        writePasswordForPasswordField(password);
        clickLogin();
        checkSuccessful();
    }



}
