package examples.tests.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriversAndLocators {
    WebDriver driverChrome;


    @BeforeClass
    public void initialize(){
        driverChrome = new ChromeDriver();
//    WebDriver driverFirefox = new FirefoxDriver();
//    WebDriver driverEdge = new EdgeDriver();
//    WebDriver driverSafari = new SafariDriver();
    }

    @BeforeMethod
    public void navigateToUrl(){
        driverChrome.navigate().to("https://automaticityacademy.ngrok.app/");
//        driverChrome.get("https://automaticityacademy.ngrok.app/");
    }

    @Test
    public void locatorsExamples(){
        WebElement classLocator = driverChrome.findElement(By.className("mb-4"));
        WebElement idLocator = driverChrome.findElement(By.id("loginBtn"));
//        WebElement attributeNameLocator = driverChrome.findElement(By.name("nameLocator"));
        WebElement tagNameLocator = driverChrome.findElement(By.tagName("a"));
        WebElement cssSelector = driverChrome.findElement(By.cssSelector("#loginBtn"));
        WebElement xpathLocator = driverChrome.findElement(By.xpath("//*[@id = 'loginBtn']"));
        WebElement linkText = driverChrome.findElement(By.linkText("Log in"));
        WebElement partialLinkText = driverChrome.findElement(By.partialLinkText("Log"));
    }

    @Test
    public void moreDriverMethods(){
        String url = driverChrome.getCurrentUrl();
        String title = driverChrome.getTitle();
        System.out.println("URL: "+url);
        System.out.println("TITLE: "+title);
    }

    @AfterClass
    public void closeBrowser(){
        driverChrome.close();
    }
}
