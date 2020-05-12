package org.zpm.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.zpm.Driver.DriverHolder;

public abstract class AbstractPage {

    protected AbstractPage() {
        PageFactory.initElements(DriverHolder.INSTANCE.getDriver(), this);
    }

    protected AbstractPage open(String url){
        DriverHolder.INSTANCE.getDriver().get(url);
        return this;
    }

    protected abstract boolean atPage();

    protected static int getNumberFromStr(String s){
        return Integer.parseInt(s.replaceAll("[^0-9\\+]", ""));
    }

}
