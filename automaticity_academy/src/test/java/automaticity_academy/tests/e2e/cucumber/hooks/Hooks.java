package automaticity_academy.tests.e2e.cucumber.hooks;

import automaticity_academy.pom.base.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class Hooks {

    @Before()
    public void setUpWindow() {
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @After()
    public void quitBrowser() {
        Driver.quit();
    }
}
