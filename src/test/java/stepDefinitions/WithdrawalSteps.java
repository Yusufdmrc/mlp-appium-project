package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.WithdrawalPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class WithdrawalSteps {
    private WithdrawalPage withdrawalPage;

    private WithdrawalPage withdrawalPage() {
        if (withdrawalPage == null) {
            withdrawalPage = new WithdrawalPage(DriverFactory.getDriver());
        }
        return withdrawalPage;
    }

    @Given("User navigates to the Para Çekme page")
    public void userNavigatesToTheParaCekmePage() {
        withdrawalPage().navigateToWithdrawalPage();
    }

    @When("User adds a new IBAN with {string} and {string}")
    public void userAddsANewIBANWithAnd(String ibanNo, String shortName) {
        withdrawalPage().addNewIban(ibanNo,shortName);
    }

    @And("User withdraws {string}")
    public void userWithdraws(String price) {
        withdrawalPage().withdraw(price);
    }

    @Then("Check Successful withdrawal")
    public void checkSuccessfulWithdrawal() {
        withdrawalPage().checkSuccessfulWithdrawal();
    }


    @When("User removes the IBAN")
    public void userRemovesTheIBANWith() {
        withdrawalPage().removeIban();
    }

    @Then("Check IBAN is removed successfully")
    public void checkIBANIsRemovedSuccessfully() {
        withdrawalPage().checkRemovedIban();
    }
}
