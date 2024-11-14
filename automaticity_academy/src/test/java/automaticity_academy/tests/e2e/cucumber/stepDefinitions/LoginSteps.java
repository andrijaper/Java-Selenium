package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.Urls;
import automaticity_academy.pom.LoginPage;
import automaticity_academy.pom.NavbarPage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.utils.General;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

public class LoginSteps {

    WebDriver driver = Driver.getDriver();
    NavbarPage navbar = new NavbarPage();
    LoginPage login = new LoginPage();

    @Given("I am on the Academy Application")
    public void userNavigateToTheAcademyApplication() {
        driver.get(Urls.PRODUCTION_URL);
    }

    @When("I click on the login button in navigation bar")
    public void userClicksOnTheLoginButton() {
        navbar.clickLoginButton();
    }

    @When("I enter the email {string} in the field")
    public void userEnterTheEmail(String email) {
        login.enterEmail(email);
    }

    @When("I enter the password {string} in the field")
    public void userEnterThePassword(String password) {
        login.enterPassword(password);
    }

    @When("I click on the sign in button")
    public void whenUserClickOnTheLoginButton() {
        login.clickSignIn();
    }

    @Then("I should see invalid fields {string}")
    public void checkDisplayedFieldsMessage(String message) {
        Assert.assertEquals(login.getInvalidFieldsMessage(), message);
    }


    @Then("I should be on Dashboard page")
    public void loginShouldBeSuccess() {
        General.checkUrl(driver, Urls.PRODUCTION_URL + ApiConstans.Endpoint.DASHBOARD);
    }
}
