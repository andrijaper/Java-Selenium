package automaticity_academy.pom;

import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class DashboardPage {

    private final WebDriver driver;
    private final By headlineLocator = By.className("block");
    private final By paginationButtonsLocator = By.cssSelector(".paginated [data-pc-name='button']");
    private final By productsLocator = By.xpath("//div[@test-data='product-container']");
    private final By productImagesLocator = By.cssSelector("img");
    private final By productNamesLocator = By.tagName("h5");
    private final By starRatingsLocator = By.cssSelector(".space-x-1");
    private final By scoreRatingsLocator = By.cssSelector("span.border-2");
    private final By productDescriptionsLocator = By.tagName("p");
    private final By productPricesLocator = By.xpath("//span[contains(text(),'€')]");
    private final By oldPrice = By.xpath(".//span/span");
    private final By afterDiscount = By.xpath(".//span/p");
    private final By todayDealsHeadlineLocator = By.tagName("h4");
    private final By todaysDealLocator = By.xpath("//li/div");
    private final By productCartButtonLocator = By.xpath(".//button");
    private final By messageBoxLocator = By.cssSelector(".Toastify__toast-icon ~ div");
    private final By modalDialogLocator = By.xpath("//*[contains(@class,'p-dialog')]");
    private final By modalDialogHeaderLocator = By.className("p-dialog-title");
    private final By modalDialogBodyLocator = By.xpath("//*[contains(@class,'p-dialog-content')]");
    private final By ratingStarLocator = By.xpath("//*[@role='rating']");
    private final By fullStarLocator = By.xpath("//*[@data-testid='full-star']");
    private final By emptyStarLocator = By.xpath("//*[@data-testid='empty-star']");
    private final By halfStarLocator = By.xpath("//*[@data-testid='half-star']");
    private final By leftSliderLocator = By.xpath("//*[contains(@class,'p-slider-handle-start')]");
    private final By rightSliderLocator = By.xpath("//*[contains(@class,'p-slider-handle-end')]");
    private final By minPriceLocator = By.cssSelector(".mt-12 div:nth-child(2) span:nth-child(1)");
    private final By maxPriceLocator = By.cssSelector(".mt-12 div:nth-child(2) span:nth-child(2)");

    public DashboardPage() {
        this.driver = Driver.getDriver();
    }

    public By getProductImagesLocator() {
        return productImagesLocator;
    }

    public By getStarRatingLocator() {
        return starRatingsLocator;
    }

    public By getProductNamesLocator() {
        return productNamesLocator;
    }

    public By getScoreRatingsLocator() {
        return scoreRatingsLocator;
    }

    public By getProductDescriptionsLocator() {
        return productDescriptionsLocator;
    }

    public By getProductPricesLocator() {
        return productPricesLocator;
    }

    public By getPriceAfterDiscount() {
        return afterDiscount;
    }

    public By getTodayDealsHeadlineLocator() {
        return todayDealsHeadlineLocator;
    }

    public By getOldPrice() {
        return oldPrice;
    }

    public By getProductCartButtonLocator() {
        return productCartButtonLocator;
    }

    public By getRatingStarLocator() {
        return ratingStarLocator;
    }

    public By getHalfStarLocator() {
        return halfStarLocator;
    }

    public By getFullStarLocator() {
        return fullStarLocator;
    }

    public By getEmptyStarLocator() {
        return emptyStarLocator;
    }

    public String getMinPrice() {
        return Waits.waitForElementClickability(driver, minPriceLocator, Duration.ofSeconds(10)).getText();
    }

    public String getMaxPrice() {
        return Waits.waitForElementClickability(driver, maxPriceLocator, Duration.ofSeconds(10)).getText();
    }

    public WebElement getLeftSlider() {
        return Waits.waitForElementClickability(driver, leftSliderLocator, Duration.ofSeconds(10));
    }

    public WebElement getRightSlider() {
        return Waits.waitForElementClickability(driver, rightSliderLocator, Duration.ofSeconds(10));
    }

    public WebElement getModalDialog() {
        return Waits.waitForElementVisibility(driver, modalDialogLocator, Duration.ofSeconds(10));
    }

    public WebElement getModalDialogHeader() {
        return Waits.waitForElementVisibility(driver, modalDialogHeaderLocator, Duration.ofSeconds(10));
    }

    public WebElement getModalDialogBody() {
        return Waits.waitForElementVisibility(driver, modalDialogBodyLocator, Duration.ofSeconds(10));
    }

    public WebElement getMessageBox() {
        return Waits.waitForElementVisibility(driver, messageBoxLocator, Duration.ofSeconds(10));
    }

    public List<WebElement> getTodaysDeals() {
        return Waits.waitForElementsVisibility(driver, todaysDealLocator, Duration.ofSeconds(10));
    }

    public WebElement getHeadline() {
        return Waits.waitForElementPresence(driver, headlineLocator, Duration.ofSeconds(10));
    }

    public List<WebElement> getPaginationButtons() {
        return Waits.waitForElementsVisibility(driver, paginationButtonsLocator, Duration.ofSeconds(10));
    }

    public List<WebElement> getProducts() {
        return Waits.waitForElementsVisibility(driver, productsLocator, Duration.ofSeconds(10));
    }

    public WebElement getPaginationButton(int pageNumber) {
        return Waits.waitForElementClickability(driver, By.xpath("//*[contains(@class,'paginated')]//*[@data-pc-name='button']/span[text()='" + pageNumber + "']/.."), Duration.ofSeconds(10));
    }

    public boolean checkCurrentPageOnPaginationBar(int pageNumber) {
        return Waits.waitForElementVisibility(driver, By.xpath("//*[contains(@class,'paginated')]//*[@data-pc-name='button']/span[text()='" + pageNumber + "']/.."), Duration.ofSeconds(10)).getCssValue("background-color").equalsIgnoreCase("rgba(158, 160, 246, 1)");
    }

    public void moveSlider(WebElement slider, int offset) {
        String sliderClass = slider.getAttribute("class");

        System.out.println("Slider Class: " + sliderClass);

        Actions action = new Actions(driver);
        action.moveToElement(slider).clickAndHold().perform();

        System.out.println("Moved slider by offset: " + offset);

        assert sliderClass != null;
        if (sliderClass.contains("p-slider-handle-end")) {
            action.moveByOffset(-offset, 0).release().perform();
        } else {
            action.moveByOffset(offset, 0).release().perform();
        }
    }
}
