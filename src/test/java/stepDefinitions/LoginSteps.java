package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("User at home page")
    public void userAtHomePage() {
        loginPage.verifyMemberLoginButtonIsVisible();
    }

    @When("Click member login button")
    public void clickMemberLoginButton() {
        loginPage.clickMemberLoginButton();
    }

    @And("write {string} for username field")
    public void writeForUsernameField(String username) {
        loginPage.writeUsernameForUsernameField(username);
    }

    @And("write {string} for password field")
    public void writeForPasswordField(String password) {
        loginPage.writePasswordForPasswordField(password);
    }

    @And("Click login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("Check Successful login")
    public void checkSuccessfulLogin() {
        loginPage.checkSuccessful();

    }
}
