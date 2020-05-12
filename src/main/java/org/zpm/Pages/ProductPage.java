package org.zpm.Pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;

public class ProductPage extends AbstractPage{

    @FindBy(xpath = "")
    private WebElement webElement;

    @FindBy(xpath = "//li[@class=\"description_tab active\"]/a")
    private WebElement descriptionTab;

    @FindBy(xpath = "//h2[text() = \"Product Description\"]/following-sibling::p")
    private WebElement descriptionText;

    @FindBy(xpath = "//li[@class=\"reviews_tab\"]/a")
    private WebElement reviewsTab;

    @FindBy(xpath = "//h2[text() = \"Reviews\"]/following-sibling::p") // xpath = \"//h2[@text = \\\"Reviews\\\"]/following-sibling::p
    private WebElement reviewsText;

    @FindBy(xpath = "//form[@class = \"cart\"]/*[@type = \"submit\"]")
    private WebElement addToBasketButton;

    @FindBy(css = ".cartcontents")
    private WebElement basketItems;

    @FindBy(css = ".amount")
    private WebElement basketPrice;

    @FindBy(xpath = "//p[@class = \"stock in-stock\"]")
    private WebElement itemsInStock;

    @FindBy(css = "input.input-text")
    private WebElement inputNumberOfItems;

    public ProductPage() {
    }

    @Override
    public boolean atPage() {
        if (DriverHolder.INSTANCE.getDriver().getCurrentUrl().contains("/product/")) {
            return true;
        }
        return false;
    }

    public ProductPage clickOnDescriptionTab(){
        descriptionTab.click();
        return this;
    }

    public boolean checkForDescription(){
        if (descriptionText.isDisplayed()) {
            return true;
        }
        return false;
    }

    public ProductPage clickOnReviewsTab(){
        reviewsTab.click();
        return this;
    }

    public boolean checkForReviews(){
        if (reviewsText.isDisplayed()) {
            return true;
        }
        return false;
    }

    public ProductPage addToBasket() {
        try
        {
            addToBasketButton.click();
        }
        catch( StaleElementReferenceException e )
        {
            addToBasketButton.click();
        }
        return this;
    }

    public int getBasketItemsNumber(){
        return getNumberFromStr(basketItems
                .getText());
    }

    public int getNumberOfItemsInStock(){
        return getNumberFromStr(itemsInStock
                .getText());
    }

    public void setUnreachableNumberOfItems(){
        inputNumberOfItems.sendKeys(String.valueOf(getNumberOfItemsInStock()));
    }
}
