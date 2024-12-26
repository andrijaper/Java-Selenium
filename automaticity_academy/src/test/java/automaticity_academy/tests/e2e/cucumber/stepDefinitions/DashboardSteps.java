package automaticity_academy.tests.e2e.cucumber.stepDefinitions;

import automaticity_academy.constants.Messages;
import automaticity_academy.pom.DashboardPage;
import automaticity_academy.pom.base.BasePage;
import automaticity_academy.pom.base.Driver;
import automaticity_academy.pom.base.Waits;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class DashboardSteps {

    WebDriver driver = Driver.getDriver();
    DashboardPage dashboard = new DashboardPage();
    BasePage base = new BasePage();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String productName;
    String productDescription;
    String minPrice;
    String maxPrice;
    String minPriceAfter;
    String maxPriceAfter;

    @When("I check I am on the page number {int}")
    public void checkPaginationPageNumber(int pageNumber) {
        Assert.assertTrue(dashboard.checkCurrentPageOnPaginationBar(pageNumber));
    }

    @When("I click on page {int} in pagination bar")
    public void clickOnPageNumberInPaginationBar(int pageNumber) {
        js.executeScript("arguments[0].scrollIntoView(true);", dashboard.getPaginationButton(pageNumber));
//        js.executeScript("window.scrollBy(0,500)");
        dashboard.getPaginationButton(pageNumber).click();
    }

    @When("I am on a dashboard page")
    public void checkIfImOnDashBoardPage() {
        Assert.assertEquals(dashboard.getHeadline().getText(), Messages.BUY_SOME_STUFF_BRUH);
    }

    @When("I click on cart icon for product with order number {int}")
    public void addProductIntoCart(int orderNumber) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement product = dashboard.getProducts().get(orderNumber);
        Thread.sleep(6000);
        WebElement productElement = Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductCartButtonLocator()), Duration.ofSeconds(10));
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        actions.moveToElement(productElement).perform();
        if (productElement.isEnabled()){
            Assert.assertTrue((productElement).getCssValue("color").equalsIgnoreCase("rgba(75, 85, 99, 1)"));
            productElement.click();
        }
    }

    @When("I check is star rating match score rating for product with order number {int}")
    public void checkRatings(int orderNumber) throws InterruptedException {
        Thread.sleep(6000);
        WebElement product = dashboard.getProducts().get(orderNumber);
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        String scoreRating = Waits.waitForElementVisibility(driver, product.findElement(dashboard.getScoreRatingsLocator()), Duration.ofSeconds(10)).getText();
        int fullStarsNumber = Integer.parseInt(scoreRating.split("\\.")[0]);
        int halfStarNumber = Integer.parseInt(scoreRating.split("\\.")[1]);
        for (int star = 0; star < 5; star++) {
            By expectedLocator;
            if (star < fullStarsNumber) {
                expectedLocator = dashboard.getFullStarLocator();
            } else if (star == fullStarsNumber && halfStarNumber >= 5) {
                expectedLocator = dashboard.getHalfStarLocator();
            } else {
                expectedLocator = dashboard.getEmptyStarLocator();
            }
            WebElement starElement = Waits.waitForElementVisibility(driver, product.findElements(dashboard.getRatingStarLocator()).get(star), Duration.ofSeconds(10));
            WebElement expectedElement = Waits.waitForElementVisibility(driver, expectedLocator, Duration.ofSeconds(10));
            Assert.assertEquals(starElement.getAttribute("data-testid"),
                expectedElement.getAttribute("data-testid")
            );
        }
    }

    @When("I click on the image of product with order number {int}")
    public void clickOnProductImage(int orderNumber) throws InterruptedException {
        Thread.sleep(3000);
        WebElement product = dashboard.getProducts().get(orderNumber);
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        WebElement productImage = Waits.waitForElementClickability(driver, product.findElement(dashboard.getProductImagesLocator()), Duration.ofSeconds(20));
        productName = Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductNamesLocator()), Duration.ofSeconds(20)).getText();
        productDescription = Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductDescriptionsLocator()), Duration.ofSeconds(20)).getText();
        productImage.click();
    }

    @And("I check if each product has necessary elements")
    public void checkIfProductHasItsElements() throws InterruptedException {
        Thread.sleep(5000);
        for (WebElement product : dashboard.getProducts()) {
            Assert.assertTrue(Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductImagesLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductNamesLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getStarRatingLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getScoreRatingsLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductDescriptionsLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductPricesLocator()), Duration.ofSeconds(10)).isDisplayed() &&
                Waits.waitForElementVisibility(driver, product.findElement(dashboard.getProductCartButtonLocator()), Duration.ofSeconds(10)).isDisplayed());
        }
    }

    @When("I check dicount prices are calculated and shown correctly")
    public void checkDiscount() {
        List<WebElement> todayDeals = dashboard.getTodaysDeals();
        for (WebElement deal : todayDeals) {
            Assert.assertEquals(
                Math.round((Double.parseDouble(Waits.waitForElementVisibility(driver, deal.findElement(dashboard.getOldPrice()), Duration.ofSeconds(10)).getText().split("€")[0]) * 0.33) * 100.0) / 100.0,
                Double.parseDouble(Waits.waitForElementVisibility(driver, deal.findElement(dashboard.getPriceAfterDiscount()), Duration.ofSeconds(10)).getText().split("€")[0])
            );
        }
    }

    @When("I check today deals are present")
    public void areTodayDealsPresent() {
        driver.switchTo().frame(Waits.waitForElementPresence(driver, By.xpath("//*[contains(@class,'ml-10')]"), Duration.ofSeconds(10)));
        Assert.assertTrue(base.isPresent(dashboard.getTodayDealsHeadlineLocator()));
    }

    @When("I check prices in the filter bar after moving sliders")
    public void checkPricesInFilterBar() {
        minPrice = dashboard.getMinPrice();
        maxPrice = dashboard.getMaxPrice();
        System.out.println(minPrice);
        System.out.println(maxPrice);
    }

    @And("I check number of products on this page should be {int}")
    public void checkNumberOfProductsOnPage(int numberOfProducts) {
        Assert.assertEquals(dashboard.getProducts().size(), numberOfProducts);
    }

    @Then("I see prices haven't changed")
    public void arePricesMatching() {
        minPriceAfter = dashboard.getMinPrice();
        maxPriceAfter = dashboard.getMaxPrice();
        Assert.assertEquals(minPriceAfter, minPrice);
        Assert.assertEquals(maxPrice, maxPriceAfter);
    }

    @Then("I check modal dialog is displayed with product name and product description")
    public void isModalDisplayedWithProductNameAndDescription() {
        System.out.println();
        Assert.assertTrue(dashboard.getModalDialog().isDisplayed());
        Assert.assertEquals(dashboard.getModalDialogHeader().getText(), productName);
        Assert.assertEquals(dashboard.getModalDialogBody().getText(), productDescription);
    }

    @Then("I can see that product is successfully added")
    public void isProductAdded() {
        js.executeScript("arguments[0].scrollIntoView(true);", dashboard.getMessageBox());
        Assert.assertEquals(dashboard.getMessageBox().getText(), Messages.PRODUCT_ADDED);
    }

    @Then("I click on each pagination button and check it")
    public void checkPagination() {
        js.executeScript("window.scrollBy(0,500)");
        List<WebElement> paginationButtons = dashboard.getPaginationButtons();
        for (int button = 0; button < paginationButtons.size(); button++) {
            if (button == paginationButtons.size() - 1) {
                dashboard.checkCurrentPageOnPaginationBar(Integer.parseInt(paginationButtons.get(button).getText()));
                return;
            }
            dashboard.checkCurrentPageOnPaginationBar(Integer.parseInt(paginationButtons.get(button).getText()));
            dashboard.getPaginationButton(Integer.parseInt(paginationButtons.get(button).getText()) + 1).click();
        }
    }

    @Then("I click {string} slider and move it by {int}")
    public void moveSliderBy(String slider, int offset) throws InterruptedException {
        switch (slider) {
            case "left slider":
                dashboard.moveSlider(dashboard.getLeftSlider(), offset);
                break;
            case "right slider":
                dashboard.moveSlider(dashboard.getRightSlider(), offset);
                break;
        }
    }
}
