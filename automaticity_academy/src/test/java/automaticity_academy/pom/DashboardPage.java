package automaticity_academy.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final By headlineLocator = By.className("block");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeadline() {
        return Waits.waitForElementPresence(driver, headlineLocator, Duration.ofSeconds(10));
    }





}
