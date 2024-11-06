package automaticity_academy.tests.e2e.steps;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        System.out.println(driver.getTitle());
    }

    @When("User clicks on the login button")
    public void userClicksOnTheLoginButton() {
      driver.findElement(By.id("loginBtn")).click();
    }

    @And("User enter the email {string} in the field")
    public void userEnterTheEmail(String email){
      driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("User enter the password {string} in the field")
    public void userEnterThePassword(String password){
      driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User click on the login button")
    public void whenUserClickOnTheLoginButton(){
      driver.findElement(By.xpath("//button/*[text()='Sign In']")).click();
    }

    @Then("Login should be success")
    public void loginShouldBeSuccess(){
      String url = driver.getCurrentUrl();
      System.out.println(url);
      driver.quit();
    }

    @After("Quit")
    public void quitBrowser(){
        driver.quit();
    }
}
