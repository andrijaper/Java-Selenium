package automaticity_academy.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    WebDriver driver;

        public static WebElement waitForElementPresence(WebDriver driver, By locator, Duration waitInterval) {
            return new WebDriverWait(driver, waitInterval)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        public static WebElement waitForElementVisibility(WebDriver driver, By locator, Duration waitInterval) {
            return new WebDriverWait(driver, waitInterval)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public static WebElement waitForElementClickability(WebDriver driver, By locator, Duration waitInterval) {
            return new WebDriverWait(driver, waitInterval)
                .until(ExpectedConditions.elementToBeClickable(locator));
        }
}
