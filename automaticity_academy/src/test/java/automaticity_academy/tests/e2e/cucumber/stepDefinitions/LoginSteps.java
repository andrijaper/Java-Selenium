package automaticity_academy.tests.e2e.stepDefinitions;

import automaticity_academy.constants.ApiConstans;
import automaticity_academy.constants.Urls;
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

    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Given("User navigate to the Academy Application")
    public void userNavigateToTheAcademyApplication() {
        driver.get("https://automaticityacademy.ngrok.app");
    }

    @When("User clicks on the login button")
    public void userClicksOnTheLoginButton() {

    }

    @And("User enter the email {string} in the field")
    public void userEnterTheEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("User enter the password {string} in the field")
    public void userEnterThePassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User click on the login button")
    public void whenUserClickOnTheLoginButton() {
        driver.findElement(By.xpath("//button/*[text()='Sign In']")).click();
    }

    @Then("{string} message is displayed")
    public void is_displayed(String message) {
        Assert.assertEquals(Waits.waitForElementVisibility(driver, By.cssSelector("p"), Duration.ofSeconds(10)).getText().trim(), message);
    }

    @Then("Login should be success")
    public void loginShouldBeSuccess() {
        General.checkUrl(driver, Urls.PRODUCTION_URL + ApiConstans.Endpoint.DASHBOARD);
    }

    @After()
    public void quitBrowser() {
        driver.quit();
    }
}
