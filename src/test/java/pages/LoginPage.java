package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;
import utils.BaseActions;
import utils.IOSActions;

import java.time.Duration;

public class LoginPage {

    private final AppiumDriver driver;
    private final BaseActions baseActions;
    private final IOSActions iosActions;
    private final AndroidActions androidActions;

    @FindBy(xpath = "")
    WebElement memberLoginButton;

    @FindBy(id = "")
    WebElement loginButton;

    @FindBy(id = "")
    WebElement usernameBox;

    @FindBy(id = "")
    WebElement passwordBox;

    @FindBy(css = "")
    WebElement balance;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
        this.iosActions=new IOSActions((IOSDriver) driver);
        this.androidActions=new AndroidActions((AndroidDriver) driver);
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
        baseActions.waitForVisibility(balance);
    }

    public void login(String username,String password){
        clickMemberLoginButton();
        writeUsernameForUsernameField(username);
        writePasswordForPasswordField(password);
        clickLogin();
        checkSuccessful();
    }
}
