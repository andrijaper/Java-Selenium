package automaticity_academy.tests.e2e.steps;

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
    public void User_navigate_to_the_Academy_Application(){ 
        driver.get("https://automaticityacademy.ngrok.app");
        System.out.println(driver.getTitle());
    }

    @And("User clicks on the login button")
    public void User_clicks_on_the_login_button(){ 
      driver.findElement(By.id("loginBtn")).click();
    }

    @And("User enter the email")
    public void userEnterTheEmailAsJaneAtTestCom(){ 
      driver.findElement(By.id("email")).sendKeys("jane@test.com");
    }

    @And("User enter the password")
    public void userEnterThePasswordAsJanedoeAt123(){ 
      driver.findElement(By.id("password")).sendKeys("Janedoe@123");
    }

    @When("User click on the login button")
    public void whenUserClickOnTheLoginButton(){ 
      driver.findElement(By.xpath("//button/*[text()='Sign In']")).click();
    }

    @Then("Login should be success")
    public void loginShouldBeSuccess(){
      String url = driver.getCurrentUrl(); 
      System.out.println(url);
      driver.quit();
    }
}
