package examples.tests.e2e;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementProperties {

    WebDriver driver;
    WebElement loginButton;

    @BeforeClass
    public void initialize(){
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void navigateToUrl(){
        driver.navigate().to("https://automaticityacademy.ngrok.app/");
        loginButton = driver.findElement(By.id("loginBtn"));
    }

    @Test
    public void getAttribute(){
        String link = loginButton.getAttribute("href");
        System.out.println("HREF TEXT: "+link);
    }

    @Test
    public void getText(){
        WebElement paragraph = driver.findElement(By.cssSelector(".mb-2"));
        String text = paragraph.getText();
        System.out.println("PARAGRAPH TEXT: "+text);
    }

    @Test
    public void getCSSvalue(){
        String buttonColor = loginButton.getCssValue("background-color");
        System.out.println("FONT COLOR: "+buttonColor);
    }

    @Test
    public void getElementPosition(){
        Point buttonPosition = loginButton.getLocation();
//        String buttonPosition = String.valueOf(loginButton.getLocation());
        System.out.println("POSITION: "+buttonPosition);
    }

    @Test
    public void getElementSize(){
        Dimension buttonSize = loginButton.getSize();
//        String buttonSize = String.valueOf(loginButton.getSize());
        System.out.println("SIZE: "+buttonSize);
    }

    @Test
    public void getTagName(){
        String tagName = loginButton.getTagName();
        System.out.println("TAG NAME: "+tagName);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
