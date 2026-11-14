package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LotteryGamesPage;
import utils.DriverFactory;

public class LotteryGamesSteps {

    private HomePage homePage;
    private LotteryGamesPage lotteryGamesPage;

    private HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(DriverFactory.getDriver());
        }
        return homePage;
    }

    private LotteryGamesPage lotteryGamesPage() {
        if (lotteryGamesPage == null) {
            lotteryGamesPage = new LotteryGamesPage(DriverFactory.getDriver());
        }
        return lotteryGamesPage;
    }

    @When("User navigates to the {string} game page from homepage")
    public void userNavigatesToTheGamePageFromHomepage(String gameName) {
        homePage().navigateToLotteryGame(gameName);
    }

    @And("User navigates to {string} tab on game")
    public void userNavigatesToTabOnGame(String gameType) {
        lotteryGamesPage().navigateToGameType(gameType);
    }

    @And("User enters {} row by random button")
    public void userEntersRowByRandomButton(int clickCount) {
        lotteryGamesPage().enterRowByRandomButton(clickCount);
    }

    @Then("User verifies the ticket was bought successfully")
    public void userVerifiesTheTicketWasBoughtSuccessfully() {
        lotteryGamesPage().buyTicket();
    }

    @And("User verifies the receipt elements for {} draw {} column {} standart number {} superstar number")
    public void userVerifiesTheReceiptElements(
            int drawNumber,
            int columnNumber,
            int standartNumber,
            int superstarNumber
    ) {
        lotteryGamesPage().verifyReceiptElements(drawNumber, columnNumber, standartNumber, superstarNumber);
    }

    @And("User verifies the details of the ticket for {string}")
    public void userVerifiesTheDetailsOfTheTicketFor(String gameName) {
        lotteryGamesPage().verifyDetailsOfTicket(gameName);
    }
}
