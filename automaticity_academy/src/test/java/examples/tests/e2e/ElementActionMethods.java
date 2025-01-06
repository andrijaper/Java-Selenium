package examples.tests.e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class ElementActionMethods {

        WebDriver driver;
        WebElement loginButton;

        @BeforeSuite
        public void initialize(){
            driver = new ChromeDriver();
        }

        @BeforeClass
        public void navigateToUrl(){
            driver.navigate().to("https://automaticityacademy.ngrok.app/");
            loginButton = driver.findElement(By.id("loginBtn"));
        }

        @Test
        public void checkButtonAndThenClick(){
            boolean checkIsLoginButtonEnabled = loginButton.isEnabled();
            System.out.println("IS ENABLED: "+checkIsLoginButtonEnabled);

            boolean checkIsLoginButtonDisplayed = loginButton.isDisplayed();
            System.out.println("IS DISPLAYED: "+checkIsLoginButtonDisplayed);
        }

        @Test(dependsOnMethods = "checkButtonAndThenClick")
        public void clearLoginFields() throws InterruptedException {
            loginButton.click();

            Thread.sleep(3000); // wait for 3 seconds

            WebElement email = driver.findElement(By.id("email"));
            email.sendKeys("jane@test.com");

            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("Janedoe@123");

            email.clear();
            password.clear();
        }

        @Test(dependsOnMethods = "checkButtonAndThenClick")
        public void login() throws InterruptedException {
            loginButton.click();

            Thread.sleep(3000); // wait for 3 seconds

            driver.findElement(By.id("email")).sendKeys("jane@test.com"); // finding elements after refresh
            driver.findElement(By.id("password")).sendKeys("Janedoe@123");

            WebElement signIn = driver.findElement(By.className("p-button-label"));

            signIn.click();
        }

        @Test
        public void isSelected() throws InterruptedException {
            driver.get("https://demo.applitools.com/");

            Thread.sleep(3000);

            WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
            checkbox.click();

            boolean isCheckboxSelected = checkbox.isSelected();
            System.out.println(isCheckboxSelected);
        }

//        @AfterClass
//        public void closeBrowser(){e
//            driver.close();
//        }
    }
