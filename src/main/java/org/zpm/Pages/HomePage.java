package org.zpm.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;
import org.zpm.URLConstants;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AbstractPage {

    @FindBy(id = "menu-item-40")
    private WebElement shopMenuItem;

    @FindBy(xpath = "//ul[@class=\"products\"]")
    private List<WebElement> productArrivals;

    @FindBy(xpath = "//ul[@class=\"products\"]//img")
    private List<WebElement> productArrivalsImg;


    @FindBy(xpath = "//ul[@class=\"products\"]//img/ancestor::a")
    private List<WebElement> productImgLink;

    public HomePage open() {
        super.open(URLConstants.BASE_URL);
        return this;
    }

    public ShopPage clickShopMenuItem(){
        shopMenuItem.click();
        return new ShopPage();
    }

    public boolean atPage(){
        if (DriverHolder.INSTANCE.getDriver().getCurrentUrl().equals(URLConstants.BASE_URL)) {
            return true;
        }
        return false;
    }

    public int countArrivals(){
        for (WebElement element:productArrivals) {
            waitForVisible(element);
        }
        return productArrivals.size();
    }

    public ProductPage clickOnProduct(){
        productImgLink.get(0).click();
        return new ProductPage();
    }
}
