package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.pom.NavbarPage;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class NavbarSteps {

    NavbarPage navbar = new NavbarPage();

    @When("I click on the dashboard button in navigation bar")
    public void userClicksOnTheLoginButton() {
        navbar.getDashboardButton().click();
    }

    @When("I click on navbar dropdown menu")
    public void clickOnDropdownMenu() {
        navbar.getNavbarDropdownMenuButton().click();
        Assert.assertTrue(navbar.getNavbarDropdownMenu().isDisplayed());
    }

    @When("I click on log out")
    public void clickOnLogoutButton() {
        navbar.getLogoutButton().click();
    }


}
