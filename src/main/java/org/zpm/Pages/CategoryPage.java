package org.zpm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;
import org.zpm.URLConstants;

import java.util.List;

public class CategoryPage extends AbstractPage {

    @FindBy(css = ".count")
    private WebElement numberOfProducts;

    public CategoryPage() {
    }

    public int getNumberOfProducts(){
        return getNumberFromStr(numberOfProducts.getText());
    }

    public int countArrivals(){
        for (WebElement element:productTitles) {
            waitForVisible(element);
        }
        return productTitles.size();
    }

    @Override
    public boolean atPage() {
        if (DriverHolder.INSTANCE.getDriver()
                .getCurrentUrl()
                .equals(URLConstants.BASE_URL +  "/product-category/*/")) {
            return true;
        }
        return false;
    }
}
