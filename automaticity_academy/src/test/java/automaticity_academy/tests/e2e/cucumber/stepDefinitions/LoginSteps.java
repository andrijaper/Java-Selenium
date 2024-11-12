package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.Urls;
import automaticity_academy.pom.LoginPage;
import automaticity_academy.pom.NavbarPage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import automaticity_academy.utils.General;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import io.cucumber.java.Before;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver = Driver.getDriver();
    NavbarPage navbar = new NavbarPage();
    LoginPage login = new LoginPage();

    @Given("I am on the Academy Application Login window")
    public void userNavigateToTheAcademyApplication() {
        driver.get(Urls.PRODUCTION_URL + ApiConstans.Endpoint.LOGIN);
    }

    @When("I click on the login button")
    public void userClicksOnTheLoginButton() {
        navbar.getLoginButton();
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

    @Then("I should see invalid email {string}")
    public void checkDisplayedEmailMessage(String message) {
        Assert.assertEquals(login.getInvalidEmailMessage(), message);
    }

    @Then("I should see invalid password {string}")
    public void checkDisplayedPasswordMessage(String message) {
        Assert.assertEquals(login.getInvalidPasswordlMessage(), message);
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
