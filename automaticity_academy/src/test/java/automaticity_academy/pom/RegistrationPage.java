package automaticity_academy.pom;

import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final By usernameFieldLocator = By.id("username");
    private final By registerButtonLocator = By.className("p-button-label");
    private final By successRegistrationMessageLocator = By.cssSelector(".surface-card .mt-4");
    private final By invalidUsernamelMessageLocator = By.xpath("//*[@id='username']/..//p");

    public RegistrationPage() {
        this.driver = Driver.getDriver();
    }

    public WebElement getUsernameField() {
        return Waits.waitForElementVisibility(driver, usernameFieldLocator, Duration.ofSeconds(10));
    }

    public WebElement getRegisterButton() {
        return Waits.waitForElementClickability(driver, registerButtonLocator, Duration.ofSeconds(10));
    }

    public WebElement getSuccessRegistrationMessage() {
        return Waits.waitForElementVisibility(driver, successRegistrationMessageLocator, Duration.ofSeconds(10));
    }

    public String getInvalidUsernamelMessage() {
        return Waits.waitForElementVisibility(driver, invalidUsernamelMessageLocator, Duration.ofSeconds(10)).getText().trim();
    }

    public void enterUsername(String username) {
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
    }


}
