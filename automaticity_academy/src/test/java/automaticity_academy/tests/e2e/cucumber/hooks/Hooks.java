package automaticity_academy.tests.e2e.cucumber.hooks;

import automaticity_academy.constants.Urls;
import automaticity_academy.pom.NavbarPage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.utils.General;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Hooks {

    NavbarPage navbar = new NavbarPage();
    WebDriver driver = Driver.getDriver();
    String url = Urls.PRODUCTION_URL + "/";

    @Before()
    public void setUpWindow() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    }

    @Before("@NavbarFeature or @DashboardFeature")
    public void goToHomePage() {
        driver.get(url);
        General.checkUrl(driver, url);
    }

    @Before("@LoginFeature")
    public void goToLoginForm() {
        driver.get(url);
        General.checkUrl(driver, url);
        navbar.getLoginButton().click();
    }

    @Before("@RegistrationFeature")
    public void goToRegistrationForm() {
        driver.get(url);
        General.checkUrl(driver, url);
        navbar.getRegisterButton().click();
    }

    @Before("@HigherResolution")
    public void setViewPort() {
        Dimension desktopSize = new Dimension(3840, 2160);
        driver.manage().window().setSize(desktopSize);
    }

    @After("not @A")
    public void quitBrowser() {
        Driver.quit();
    }
}
