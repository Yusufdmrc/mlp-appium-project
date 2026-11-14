package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TicketInquiryPage;
import utils.DriverFactory;

public class TicketInquirySteps {

    private TicketInquiryPage ticketInquiryPage;

    private TicketInquiryPage ticketInquiryPage() {
        if (ticketInquiryPage == null) {
            ticketInquiryPage = new TicketInquiryPage(DriverFactory.getDriver());
        }
        return ticketInquiryPage;
    }

    @Given("User goes to the Lotteries page")
    public void userGoesToTheLotteriesPage() {
        ticketInquiryPage().navigateToLotteryResults();
    }

    @When("User queries ticket by {string}")
    public void userQueriesTicketBy(String serialNumber) {
        ticketInquiryPage().inquireTicket(serialNumber);
    }

    @Then("The user checks if the ticket {string} has been successfully viewed")
    public void theUserChecksIfTheTicketHasBeenSuccessfullyViewed(String statusText) {
        ticketInquiryPage().checkTicketDetail(statusText);
    }
}
