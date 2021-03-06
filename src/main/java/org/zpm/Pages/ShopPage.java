package org.zpm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.zpm.Driver.DriverHolder;
import org.zpm.URLConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopPage extends AbstractPage {

    @FindBy(xpath = "//nav/a[contains(text(), 'Home')]")
    private WebElement homeLink;

    @FindBy(id = "max_price")
    private WebElement filterMaxPriceInput;

    @FindBy(css = "span.to")
    private WebElement maxPriceText;

    @FindBy(css = "button.button")
    private WebElement priceSubmitButton;

    @FindBy(css = ".price > .woocommerce-Price-amount")
    private List<WebElement> pricesOfProductsList;

    @FindBy(css = ".price > ins > span")
    private List<WebElement> lowerPriceList;

    @FindBy(css = ".product-categories > li > a")
    List<WebElement> categoryLink;

    @FindBy(css = ".orderby")
    WebElement sortSelect;

    @FindBy(css = ".ui-slider-range")
    WebElement slider;

    public ShopPage open() {
        super.open(URLConstants.SHOP_PAGE);
        return this;
    }

    public ShopPage changeMaxPriceTo() {
        int width=slider.getSize().getWidth();
        Actions move = new Actions(DriverHolder.INSTANCE.getDriver());
        move.moveToElement(slider, 60, 0) // мышка с центра элемента пермещяется вправо на 60 пикс
                .click()                                 // клик
                .build()
                .perform();
        priceSubmitButton.submit();
        return this;
    }

    public int getMaxPrice() {
        waitForVisible(maxPriceText);
        return getNumberFromStr(maxPriceText.getText().trim());
    }

    public HomePage clickHomeLink() {
        homeLink.click();
        return new HomePage();
    }

    public boolean priceOfAllProductsBetween(int min, int max) {
        waitForVisibleForList(pricesOfProductsList);
        waitForVisibleForList(lowerPriceList);
        List<WebElement> list = new ArrayList<>();
        list.addAll(lowerPriceList);
        list.addAll(pricesOfProductsList);
        return !(list.stream()
                .mapToInt(x -> getNumberFromStr(x.getText()) / 100)
                .anyMatch(x -> x > max || x < min));
    }

    public CategoryPage clickProductInCategory() {
        waitForVisibleForList(categoryLink);
        categoryLink.get(1).click();
        return new CategoryPage();
    }

    public ShopPage selectSortByPopularity(){
        Select drpSelect = new Select(sortSelect);
        drpSelect.selectByValue("popularity");
        return this;
    }

    public ShopPage selectSortByAverage(){
        Select drpSelect = new Select(sortSelect);
        drpSelect.selectByValue("rating");
        return this;
    }

    public ShopPage selectSortByNewness(){
        Select drpSelect = new Select(sortSelect);
        drpSelect.selectByValue("date");
        return this;
    }

    public List<String> getProductsTitles(){
        waitForVisibleForList(productTitles);
        List<String> strings = productTitles.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        return strings;
    }

    @Override
    public boolean atPage() {
        if (DriverHolder.INSTANCE.getDriver().getCurrentUrl().equals(URLConstants.SHOP_PAGE)) {
            return true;
        }
        return false;
    }
}
