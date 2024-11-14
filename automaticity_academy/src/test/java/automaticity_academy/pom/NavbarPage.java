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
    private final By navbarDropdownMenuButton = By.cssSelector(".relative button");
    private final By navbarDropdownMenu = By.className("absolute");
    private final By navbarDropdownLogoutButton = By.xpath("//*[contains(@class,'absolute')]//button[text()='Log Out']");

    public NavbarPage() {
        this.driver = Driver.getDriver();
    }

    public WebElement getDashboardButton() {
        return Waits.waitForElementClickability(driver, dashboardButtonLocator, Duration.ofSeconds(10));
    }

    public WebElement getRegisterButton() {
        return Waits.waitForElementClickability(driver, registerButtonLocator, Duration.ofSeconds(10));
    }

    public WebElement getLoginButton() {
        return Waits.waitForElementClickability(driver, loginButtonLocator, Duration.ofSeconds(10));
    }

    public WebElement getNavbarDropdownMenuButton() {
        return Waits.waitForElementClickability(driver, navbarDropdownMenuButton, Duration.ofSeconds(10));
    }

    public WebElement getNavbarDropdownMenu() {
        return Waits.waitForElementVisibility(driver, navbarDropdownMenu, Duration.ofSeconds(10));
    }

    public WebElement getLogoutButton() {
        return Waits.waitForElementVisibility(driver, navbarDropdownLogoutButton, Duration.ofSeconds(10));
    }
}
