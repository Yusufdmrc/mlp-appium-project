package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.BaseActions;

import java.time.Duration;

public class HomePage {

    private final BaseActions baseActions;

    // -------------------- BOTTOM NAVIGATION / HOME --------------------

    @iOSXCUITFindBy(accessibility = "sayisal_loto_home")
    @AndroidFindBy(accessibility = "sayisal_loto_home")
    private WebElement sayisalLotoHomeButton;

    @iOSXCUITFindBy(accessibility = "super_loto_home")
    @AndroidFindBy(accessibility = "super_loto_home")
    private WebElement superLotoHomeButton;

    @iOSXCUITFindBy(accessibility = "sans_topu_home")
    @AndroidFindBy(accessibility = "sans_topu_home")
    private WebElement sansTopuHomeButton;

    @iOSXCUITFindBy(accessibility = "on_numara_home")
    @AndroidFindBy(accessibility = "on_numara_home")
    private WebElement onNumaraHomeButton;

    // -------------------- CONSTRUCTOR --------------------

    public HomePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions = new BaseActions(driver);
    }

    // -------------------- PAGE ACTIONS --------------------

    /**
     * Navigates to the given lottery game page from the home screen.
     * Supported game names: "Sayisal Loto", "Super Loto", "Sans Topu", "On Numara"
     *
     * @param gameName display name of the game to navigate to
     */
    public void navigateToLotteryGame(String gameName) {
        WebElement target = resolveGameButton(gameName);
        baseActions.click(target);
    }

    private WebElement resolveGameButton(String gameName) {
        switch (gameName) {
            case "Sayisal Loto" -> { return sayisalLotoHomeButton; }
            case "Super Loto"   -> { return superLotoHomeButton; }
            case "Sans Topu"    -> { return sansTopuHomeButton; }
            case "On Numara"    -> { return onNumaraHomeButton; }
            default -> throw new IllegalArgumentException(
                    "Unsupported game name: \"" + gameName + "\". " +
                    "Expected one of: Sayisal Loto, Super Loto, Sans Topu, On Numara"
            );
        }
    }
}
