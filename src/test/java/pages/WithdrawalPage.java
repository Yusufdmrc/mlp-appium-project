package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.BaseActions;
import utils.ConfigReader;
import utils.IOSActions;

import java.time.Duration;

public class WithdrawalPage {

    private final BaseActions baseActions;

    @iOSXCUITFindBy(accessibility = "Menü")
    @AndroidFindBy(accessibility = "Menü")
    WebElement menuButton;
    @iOSXCUITFindBy(accessibility = "withdrawal")
    @AndroidFindBy(accessibility = "withdrawal")
    WebElement withdrawalMenuButton;
    @iOSXCUITFindBy(accessibility = "+ YENİ̇ EKLE")
    @AndroidFindBy(accessibility = "+ YENİ̇ EKLE")
    WebElement newAddButton;
    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    @AndroidFindBy(className = "XCUIElementTypeTextField")
    WebElement ibanField;
    @iOSXCUITFindBy(accessibility = "DOĞRULA")
    @AndroidFindBy(accessibility = "DOĞRULA")
    WebElement verifyButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"Kısa isim oluştur\"`]")
    @AndroidFindBy(androidDataMatcher = "**/XCUIElementTypeTextField[`value == \"Kısa isim oluştur\"`]")
    WebElement shortNameField;
    @iOSXCUITFindBy(accessibility = "KAYDET")
    @AndroidFindBy(accessibility = "KAYDET")
    WebElement saveButton;
    @iOSXCUITFindBy(accessibility = "Para Çekme")
    @AndroidFindBy(accessibility = "Para Çekme")
    WebElement drawMoneyButton;
    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    @AndroidFindBy(className = "XCUIElementTypeTextField")
    WebElement amountField;
    @iOSXCUITFindBy(accessibility = "ONAYLA")
    @AndroidFindBy(accessibility = "ONAYLA")
    WebElement confirmButton;
    @iOSXCUITFindBy(accessibility = "Teşekkürler! Para çekme talebiniz alınmıştır.")
    @AndroidFindBy(accessibility = "Teşekkürler! Para çekme talebiniz alınmıştır.")
    WebElement verifyText;
    @iOSXCUITFindBy(accessibility = "Delete bank account")
    @AndroidFindBy(accessibility = "Delete bank account")
    WebElement deleteIbanButton;
    @iOSXCUITFindBy(accessibility = "TAMAM")
    @AndroidFindBy(accessibility = "TAMAM")
    WebElement deleteOkButton;
    @iOSXCUITFindBy(accessibility = "Kayıtlı bir banka hesabın bulunmuyor.")
    @AndroidFindBy(accessibility = "Kayıtlı bir banka hesabın bulunmuyor.")
    WebElement verifyDeleteText;

    public WithdrawalPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    public void navigateToWithdrawalPage() {
        baseActions.click(menuButton);
        baseActions.click(withdrawalMenuButton);
    }


    public void addNewIban(String ibanNo, String shortName) {
        baseActions.click(newAddButton);
        ibanField.sendKeys(getWithdrawalValue(ibanNo));
        baseActions.click(verifyButton);
        shortNameField.sendKeys(getWithdrawalValue(shortName));
        baseActions.click(saveButton);
    }

    public void withdraw(String price) {
        baseActions.click(drawMoneyButton);
        amountField.sendKeys(getWithdrawalValue(price));
        baseActions.click(confirmButton);
    }

    public void checkSuccessfulWithdrawal() {
        baseActions.waitForVisibility(verifyText);
    }

    private String getWithdrawalValue(String key) {
        if (key == null) {
            return "";
        }
        switch (key.toLowerCase()) {
            case "ibanno":
                return ConfigReader.get("iban.no");
            case "shortname1":
                return ConfigReader.get("short.name1");
            case "price1":
                return ConfigReader.get("price1");
            case "empty":
                return "";
            default:
                return key;
        }
    }

    public void removeIban() {
        baseActions.click(deleteIbanButton);
        baseActions.click(deleteOkButton);
    }

    public void checkRemovedIban() {
        baseActions.waitForVisibility(verifyDeleteText);
    }
}
