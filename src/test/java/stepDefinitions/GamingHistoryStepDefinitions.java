package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GamingHistoryPage;
import pages.WithdrawalPage;
import utils.DriverFactory;

public class GamingHistoryStepDefinitions {

    private GamingHistoryPage gamingHistoryPage;

    private GamingHistoryPage gamingHistoryPage() {
        if (gamingHistoryPage == null) {
            gamingHistoryPage = new GamingHistoryPage(DriverFactory.getDriver());
        }
        return gamingHistoryPage;
    }

    @Given("User navigates to the Gaming History page")
    public void userNavigatesToTheGamingHistoryPage() {
    }

    @When("The user filters by selecting the {string} {string} and {string}")
    public void theUserFiltersBySelectingTheAnd(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
    }

    @When("User clicks to the detail button")
    public void userClicksToTheDetailButton() {
    }

    @Then("The user verifies the ticket detail {string} according to the selected  {string}")
    public void theUserVerifiesTheTicketDetailAccordingToTheSelected(String arg0, String arg1, String arg2, String arg3) {
    }

    @When("The user filters by selecting the {string}")
    public void theUserFiltersBySelectingThe(String arg0, String arg1) {
    }

    @Then("User confirms successful filtering of game {string}")
    public void userConfirmsSuccessfulFilteringOfGame(String arg0, String arg1) {
    }
}
