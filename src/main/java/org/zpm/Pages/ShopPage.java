package org.zpm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;
import org.zpm.URLConstants;

public class ShopPage extends AbstractPage{

    @FindBy(xpath = "//nav/a[contains(text(), 'Home')]")
    private WebElement homeLink;

    public ShopPage open() {
        super.open(URLConstants.SHOP_PAGE);
        return this;
    }

    public HomePage clickHomeLink(){
        homeLink.click();
        return new HomePage();
    }

    @Override
    public boolean atPage() {
        if (DriverHolder.INSTANCE.getDriver().getCurrentUrl().equals(URLConstants.SHOP_PAGE)) {
            return true;
        }
        return false;
    }
}
