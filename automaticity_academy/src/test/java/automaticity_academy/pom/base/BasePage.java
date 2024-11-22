package automaticity_academy.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BasePage {

    WebDriver driver;

    public BasePage() {
        this.driver = Driver.getDriver();
    }

    public boolean isPresent(By elementLocator) {
        try {
            Waits.waitForElementVisibility(driver, elementLocator, Duration.ofSeconds(10));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
