package automaticity_academy.pom;

import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final By emailFieldLocator = By.id("email");
    private final By passwordFieldLocator = By.id("password");
    private final By signInButtonLocator = By.className("p-button-label");
    private final By forgotPasswordLinkLocator = By.xpath("//a[text()='Forgot your password?']");
    private final By invalidEmailMessageLocator = By.xpath("//*[@id='email']/..//p");
    private final By invalidPasswordlMessageLocator = By.xpath("//*[@id='password']/..//p");
    private final By invalidFieldsMessageLocator = By.cssSelector(".flex.flex-column.text-center p");

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public WebElement getEmailField() {
        return Waits.waitForElementVisibility(driver, emailFieldLocator, Duration.ofSeconds(10));
    }

    public WebElement getPasswordField() {
        return Waits.waitForElementVisibility(driver, passwordFieldLocator, Duration.ofSeconds(10));
    }

    public WebElement getSignInButton() {
        return Waits.waitForElementClickability(driver, signInButtonLocator, Duration.ofSeconds(10));
    }

    public WebElement getForgotPasswordLink() {
        return Waits.waitForElementClickability(driver, forgotPasswordLinkLocator, Duration.ofSeconds(10));
    }

    public String getInvalidFieldsMessage() {
        return Waits.waitForElementVisibility(driver, invalidFieldsMessageLocator, Duration.ofSeconds(10)).getText().trim();
    }

    public String getInvalidPasswordlMessage() {
        return Waits.waitForElementVisibility(driver, invalidPasswordlMessageLocator, Duration.ofSeconds(10)).getText().trim();
    }

    public String getInvalidEmailMessage() {
        return Waits.waitForElementVisibility(driver, invalidEmailMessageLocator, Duration.ofSeconds(10)).getText().trim();
    }

    public void enterEmail(String email) {
        getEmailField().sendKeys(email);
    }

    public void enterPassword(String password) {
        getPasswordField().sendKeys(password);
    }

    public void clickSignIn() {
        getSignInButton().click();
    }

    public void clickForgotPassword() {
        getForgotPasswordLink().click();
    }

}
