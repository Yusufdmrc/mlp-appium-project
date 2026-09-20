package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.BaseActions;

import java.time.Duration;

public class DrawResultsPage {
    private final BaseActions baseActions;

    public DrawResultsPage(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.baseActions= new BaseActions(driver);
    }

    @iOSXCUITFindBy(accessibility = "draw_results")
    @AndroidFindBy(accessibility  = "draw_results")
    WebElement drawResultPage;

    WebElement sayisalLotoFilter;
    WebElement superLotoFilter;
    WebElement milliPiyangoFilter;
    WebElement sansTopuFilter;
    WebElement onNumaraFilter;
    WebElement hizliOnNumaraFilter;
    WebElement allGamesFilter;
    WebElement filterButton;


    public void navigateToDrawResults() {
        baseActions.click(drawResultPage);
    }

    public void filterByGame(String game) {
        WebElement filterElement;
        switch (game.toLowerCase()){
            case "sayısal loto":
                filterElement = sayisalLotoFilter;
                break;
            case "süper loto":
                filterElement = superLotoFilter;
                break;
            case "milli piyango":
                filterElement = milliPiyangoFilter;
                break;
            case "şans topu":
                filterElement = sansTopuFilter;
                break;
            case "on numara":
                filterElement = onNumaraFilter;
                break;
            case "hızlı on numara":
                filterElement = hizliOnNumaraFilter;
                break;
            case "tüm oyunlar":
                filterElement = allGamesFilter;
                break;
            default:
                throw new IllegalArgumentException("Invalid game: " + game);
        }
        baseActions.click(filterElement);
        baseActions.click(filterButton);
    }

    public void verifyResultsBelongToGame() {
    }
}
