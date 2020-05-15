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

    @Override
    protected boolean atPage() {
        return false;
    }

    public int getNumberOfProducts(){
        System.out.println(getNumberFromStr(numberOfProducts.getText()));
        return getNumberFromStr(numberOfProducts.getText());
    }

    public int countArrivals(){
    waitForVisibleForList(productTitles);
        System.out.println(productTitles.size());
        return productTitles.size();
    }

    public boolean atHtmlPage() {
        System.out.println(DriverHolder.INSTANCE.getDriver()
                .getCurrentUrl());
        if (DriverHolder.INSTANCE.getDriver()
                .getCurrentUrl()
                .equals("http://practice.automationtesting.in/product-category/html/")) {
            return true;
        }
        return false;
    }
}
