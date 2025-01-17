package automaticity_academy.tests.e2e;

import automaticity_academy.constants.ApiConstants;
import automaticity_academy.constants.Messages;
import automaticity_academy.constants.Urls;
import automaticity_academy.constants.User;
import automaticity_academy.pom.DashboardPage;
import automaticity_academy.pom.LoginPage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.utils.General;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    WebDriver driver;
    LoginPage login;
    DashboardPage dashboard;

    @BeforeClass
    public void initialize() {
        driver = Driver.getDriver();
        dashboard = new DashboardPage();
        login = new LoginPage();
    }

    @BeforeMethod
    public void openLoginPage() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get(Urls.PRODUCTION_URL + ApiConstants.Endpoint.LOGIN);
    }

    @Test
    public void successfullLogin() {
        login.enterEmail(User.TEST_USER.getEmail());
        login.enterPassword(User.TEST_USER.getPassword());
        login.clickSignIn();
        General.checkUrl(Driver.getDriver(), Urls.PRODUCTION_URL + ApiConstants.Endpoint.DASHBOARD);
        General.checkMessage(dashboard.getHeadline(), Messages.BUY_SOME_STUFF_BRUH);
    }

    @AfterClass
    public void closeAll() {
        Driver.quit();
    }


}
