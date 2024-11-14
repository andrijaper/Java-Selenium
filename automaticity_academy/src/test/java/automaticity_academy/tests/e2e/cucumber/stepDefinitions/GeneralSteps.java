package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.pom.base.Driver;
import automaticity_academy.utils.General;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class GeneralSteps {

    WebDriver driver = Driver.getDriver();

    @Then("I should be on the page with this url: {string}")
    public void checkPageUrl(String url) {
        General.checkUrl(driver, url);
    }


}
