package automaticity_academy.pom;

import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class NavbarPage {

    private final WebDriver driver;
    private final By dashboardButtonLocator = By.xpath("//a[text()='Dashboard']");
    private final By registerButtonLocator = By.xpath("//a[text()='Register']");
    private final By loginButtonLocator = By.id("loginBtn");

    public NavbarPage() {
        this.driver = Driver.getDriver();
    }

    public WebElement getDashboardButton() {
        return driver.findElement(dashboardButtonLocator);
    }

    public WebElement getRegisterButton() {
        return driver.findElement(registerButtonLocator);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButtonLocator);
    }

    public void clickLoginButton() {
        Waits.waitForElementClickability(driver, loginButtonLocator, Duration.ofSeconds(10)).click();
    }

    public void clickRegisterButton() {
        Waits.waitForElementClickability(driver, registerButtonLocator, Duration.ofSeconds(10)).click();
    }

    public void clickDashboardButton() {
        Waits.waitForElementClickability(driver, dashboardButtonLocator, Duration.ofSeconds(10)).click();
    }
}
