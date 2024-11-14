package automaticity_academy.tests.e2e.cucumber.hooks;

import automaticity_academy.constants.Urls;
import automaticity_academy.pom.NavbarPage;
import automaticity_academy.pom.base.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class Hooks {

    NavbarPage navbar = new NavbarPage();

    @Before()
    public void setUpWindow() {
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @Before("@LoginFeature")
    public void goToLoginForm() {
        Driver.getDriver().get(Urls.PRODUCTION_URL);
        navbar.clickLoginButton();
    }

    @Before("@RegistrationFeature")
    public void goToRegistrationForm() {
        Driver.getDriver().get(Urls.PRODUCTION_URL);
        navbar.clickRegisterButton();
    }

    @After()
    public void quitBrowser() {
        Driver.quit();
    }
}
