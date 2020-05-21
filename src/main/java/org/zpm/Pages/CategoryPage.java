package org.zpm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;

public class CategoryPage extends AbstractPage {

    @FindBy(css = ".count")
    private WebElement numberOfProducts;

    public CategoryPage() {
    }

    @Override
    protected boolean atPage() {
        return false;
    }

    public int getNumberOfProducts(){
        return getNumberFromStr(numberOfProducts.getText());
    }

    public int countArrivals(){
    waitForVisibleForList(productTitles);
        return productTitles.size();
    }

    public boolean atHtmlPage() {
        if (DriverHolder.INSTANCE.getDriver()
                .getCurrentUrl()
                .equals("http://practice.automationtesting.in/product-category/html/")) {
            return true;
        }
        return false;
    }
}
