package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.PasswordRetrievalPage;
import pages.PasswordUpdatePage;
import utils.DriverFactory;
import utils.LoginHelper;

public class LoginSteps {

    private LoginPage loginPage;
    private PasswordRetrievalPage passwordRetrievalPage;
    private PasswordUpdatePage passwordUpdatePage;

    private LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverFactory.getDriver());
        }
        return loginPage;
    }

    private PasswordRetrievalPage passwordRetrievalPage() {
        if (passwordRetrievalPage == null) {
            passwordRetrievalPage = new PasswordRetrievalPage(DriverFactory.getDriver());
        }
        return passwordRetrievalPage;
    }

    private PasswordUpdatePage passwordUpdatePage() {
        if (passwordUpdatePage == null) {
            passwordUpdatePage = new PasswordUpdatePage(DriverFactory.getDriver());
        }
        return passwordUpdatePage;
    }

    @Given("User at home page")
    public void userAtHomePage() {
        loginPage().verifyMemberLoginButtonIsVisible();
    }

    @When("Click member login button")
    public void clickMemberLoginButton() {
        loginPage().clickMemberLoginButton();
    }

    @And("write {string} for username field")
    public void writeForUsernameField(String username) {
        loginPage().writeUsernameForUsernameField(LoginHelper.getUserName(username));
    }

    @And("write {string} for password field")
    public void writeForPasswordField(String password) {
        loginPage().writePasswordForPasswordField(LoginHelper.getPassword(password));
    }

    @And("Click login button")
    public void clickLoginButton() {
        loginPage().clickLogin();
    }

    @Then("Check Successful login")
    public void checkSuccessfulLogin() {
        loginPage().checkSuccessful();
    }

    @Then("Check {string} message about credentials not valid")
    public void checkMessageAboutCredentialsNotValid(String expectedMessage) {
        loginPage().checkErrorMessage(expectedMessage);
    }

    @Then("Check unsuccessful login")
    public void checkUnsuccessfulLogin() {
        loginPage().checkUnsuccessfulLogin();
    }

    // -------------------- PASSWORD RETRIEVAL STEPS --------------------

    @And("User navigates to the Password Retrieval Page")
    public void userNavigatesToThePasswordRetrievalPage() {
        passwordRetrievalPage().navigateToPasswordRetrievalPage();
    }

    @Then("User verifies Password Retrieval form is not submittable when with {string} TC ID, {string} Birth Date, {string} email missing data")
    public void userVerifiesPasswordRetrievalFormIsNotSubmittable(
            String tcId,
            String birthDate,
            String email
    ) {
        passwordRetrievalPage().verifyPasswordRetrievalFormNotSubmittable(tcId, birthDate, email);
    }

    @And("User fills the Password Retrieval form with {string} {string} {string}")
    public void userFillsThePasswordRetrievalForm(
            String tcId,
            String birthDate,
            String email
    ) {
        passwordRetrievalPage().fillPasswordRetrievalForm(tcId, birthDate, email);
    }

    @And("User enters and submits OTP for password update")
    public void userEntersAndSubmitsOtpForPasswordUpdate() {
        passwordRetrievalPage().enterAndSubmitOtp();
    }

    // -------------------- PASSWORD UPDATE STEPS --------------------

    @And("User changes the password with {string} on Password Retrieval Screen and submits")
    public void userChangesThePasswordAndSubmits(String password) {
        passwordUpdatePage().changeThePasswordAndSubmit(password);
    }

    @And("User navigates to the Login Page from Password Update Screen")
    public void userNavigatesToTheLoginPageFromPasswordUpdateScreen() {
        passwordUpdatePage().navigateToLoginPage();
    }

    @And("User logs into the app with {string} and {string} credentials")
    public void userLogsIntoTheAppWithCredentials(String password, String tcIdKey) {
        loginPage().login(LoginHelper.getUserName(tcIdKey), password);
    }

    @Then("User verifies the login with {string} is successful")
    public void userVerifiesTheLoginIsSuccessful(String password) {
        loginPage().checkSuccessful();
    }
}
