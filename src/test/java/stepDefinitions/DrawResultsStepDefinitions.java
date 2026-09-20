package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DrawResultsPage;
import pages.WithdrawalPage;
import utils.DriverFactory;

public class DrawResultsStepDefinitions {
    private DrawResultsPage drawResultsPage;

    private DrawResultsPage drawResultsPage() {
        if (drawResultsPage == null) {
            drawResultsPage = new DrawResultsPage(DriverFactory.getDriver());
        }
        return drawResultsPage;
    }

    @Given("User navigates to the Draw Results page")
    public void userNavigatesToTheDrawResultPage() {
        drawResultsPage.navigateToDrawResults();
    }

    @When("User filters results by {string}")
    public void userFiltersResultsBy(String game) {
        drawResultsPage.filterByGame(game);
    }

    @Then("User verifies that the results belong to {string}")
    public void userVerifiesThatTheResultsBelongTo(String game) {
        drawResultsPage.verifyResultsBelongToGame();
    }
}
