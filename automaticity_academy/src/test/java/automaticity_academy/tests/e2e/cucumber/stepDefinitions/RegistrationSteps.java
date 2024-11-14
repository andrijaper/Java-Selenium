package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.api.RegistrationApi;
import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.Messages;
import automaticity_academy.constants.Urls;
import automaticity_academy.constants.User;
import automaticity_academy.pom.LoginPage;
import automaticity_academy.pom.NavbarPage;
import automaticity_academy.pom.RegistrationPage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.utils.General;
import automaticity_academy.utils.JsonGenerator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegistrationSteps {

    WebDriver driver = Driver.getDriver();
    RegistrationPage register = new RegistrationPage();
    NavbarPage navbar = new NavbarPage();
    LoginPage login = new LoginPage();

    String username = General.generateRandomString(8);
    String email = General.generateRandomString(8) + "@test.com";
    String password = General.generateRandomString(8);

    @When("I click on the registration button in navigation bar")
    public void userClicksOnTheLoginButton() {
        navbar.clickRegisterButton();
        General.checkUrl(driver, Urls.PRODUCTION_URL + ApiConstans.Endpoint.REGISTER);
    }

    @When("I enter random {string} in the field")
    public void enterRandomField(String fieldType) {
        switch (fieldType) {
            case "username":
                register.enterUsername(username);
                break;
            case "email":
                login.enterEmail(email);
                break;
            case "password":
                login.enterPassword(password);
                break;
        }
    }

    @When("I enter the {string} with blank space at the {string} in the field")
    public void enterFieldWithSpace(String fieldType, String position) {
        switch (fieldType) {
            case "username":
                if (position.equalsIgnoreCase("beginning")) {
                    register.enterUsername(" " + username);
                } else {
                    register.enterUsername(username + " ");
                }
                break;
            case "email":
                if (position.equalsIgnoreCase("beginning")) {
                    login.enterEmail(" " + email);
                } else {
                    login.enterEmail(email + " ");
                }
                break;
            case "password":
                if (position.equalsIgnoreCase("beginning")) {
                    login.enterPassword(" " + password);
                } else {
                    login.enterPassword(password + " ");
                }
                break;
        }
    }

    @When("I check if John Doe user is registered")
    public void isUserRegistered() {
        RegistrationApi registerApi = new RegistrationApi();
        String[][] user = User.generateUsersData(User.REGISTRATION_USER);
        JSONObject bodyJson = JsonGenerator.generateJson(user);
        Response response;
        do {
            response = registerApi.registerUser(bodyJson);
        } while (response.getStatusCode() == ApiConstans.StatusAndCode.OK.getCode());
        Assert.assertEquals(response.getStatusCode(), ApiConstans.StatusAndCode.UNPROCESSABLE.getCode());
    }

    @When("I enter the John Doe {string} in the field")
    public void enterJohnDoeCredentialsIntoField(String fieldType) {
        switch (fieldType) {
            case "username":
                register.enterUsername(User.REGISTRATION_USER.getUsername());
                break;
            case "email":
                login.enterEmail(User.REGISTRATION_USER.getEmail());
                break;
            case "password":
                login.enterPassword(User.REGISTRATION_USER.getPassword());
                break;
        }
    }

    @When("I enter the username with 256 characters in the field")
    public void enterCharactersInUsernameField() {
        String exceedingUsernameLenght = General.generateRandomString(256);
        register.enterUsername(exceedingUsernameLenght);
    }

    @When("I enter six blank spaces in password field")
    public void enterSixBlankSpacesInPasswordField() {
        login.enterPassword("      ");
    }

    @When("I enter five characters in password field")
    public void enterFiveCharactersInPasswordField() {
        String fiveCharactersPassword = General.generateRandomString(5);
        login.enterPassword(fiveCharactersPassword);
    }

    @When("I enter the invalid {string} in the field")
    public void enterInvalidEmailInTheField(String email) {
        login.enterEmail(email);
    }

    @When("I should see invalid {string} message {string}")
    public void enterRandomField(String fieldType, String message) {
        switch (fieldType) {
            case "username":
                Assert.assertEquals(register.getInvalidUsernamelMessage(), message);
                break;
            case "email":
                Assert.assertEquals(login.getInvalidEmailMessage(), message);
                break;
            case "password":
                Assert.assertEquals(login.getInvalidPasswordlMessage(), message);
                break;
        }
    }

    @Then("I should see success registration message")
    public void isSuccessRegistrationMessageDisplayed() {
        Assert.assertTrue(register.getSuccessRegistrationMessage().isDisplayed());
        Assert.assertEquals(register.getSuccessRegistrationMessage().getText(), Messages.SUCCESSFULLY_REGISTERED);
    }

    @When("I click on the register button")
    public void whenUserClickOnTheLoginButton() {
        register.getRegisterButton().click();
    }

}
