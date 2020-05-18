package org.zpm.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.zpm.Driver.DriverHolder;
import org.zpm.Driver.DriverType;

import java.util.List;

public abstract class AbstractPage {

    @FindBy(xpath = "//h3")
    protected List<WebElement> productTitles;



    protected AbstractPage() {
        PageFactory.initElements(DriverHolder.INSTANCE.getDriver(), this);
    }



    protected AbstractPage open(String url){

       DriverHolder.INSTANCE.getDriver().get(url);
        return this;
    }

    protected AbstractPage waitForClick(WebElement webElement){
        DriverHolder.INSTANCE.getWebDriverWait()
                .until(ExpectedConditions
                        .elementToBeClickable(webElement));
        return this;
    }

    protected AbstractPage waitForVisible(WebElement webElement){
        DriverHolder.INSTANCE.getWebDriverWait()
                .until(ExpectedConditions
                        .visibilityOf(webElement));
        return this;
    }

    protected List<WebElement> waitForVisibleForList(List<WebElement> list){
        for (WebElement elem:list) {
            DriverHolder.INSTANCE.getWebDriverWait()
                    .until(ExpectedConditions
                            .visibilityOf(elem));
        }
        return list;
    }

    protected AbstractPage waitForValue(WebElement webElement){
        DriverHolder.INSTANCE.getWebDriverWait()
                .until(ExpectedConditions
                        .attributeToBeNotEmpty(webElement, "value"));
        return this;
    }

    protected abstract boolean atPage();

    protected static int getNumberFromStr(String s){
        return Integer.parseInt(s.replaceAll("[^0-9\\+]", ""));
    }

}
