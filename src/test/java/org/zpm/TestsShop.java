package org.zpm;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zpm.Pages.CategoryPage;
import org.zpm.Pages.HomePage;

import java.util.List;

public class TestsShop extends BaseTest {

    @BeforeMethod
    public void beforeTest() {
        shop = new HomePage()
                .open()
                .clickShopMenuItem();
    }

    // Test "ShopMenu_filter150to450_viewBooksWithSpecifiedPrice" doesn`t pass in Suite
    // ShopMenu_filter150to450_viewBooksWithSpecifiedPrice(org.zpm.TestsShop): element not interactable(..)
    // But works when it is run manually

    @Test(priority = 1)
    public void ShopMenu_filter150to450_viewBooksWithSpecifiedPrice() {
        shop.changeMaxPriceTo(450);
        Assert.assertTrue(shop.priceOfAllProductsBetween(150, 450));
    }

    @Test()
    public void ShopMenu_clickCategoryLink_viewParticularProduct() {
        CategoryPage category = shop.clickProductInCategory();
        Assert.assertTrue(category.atHtmlPage());
    }

    @Test()
    public void ShopMenu_clickPopularitySort_viewPopularProduct() {
        List<String> productsBefore = shop.getProductsTitles();
        shop.selectSortByPopularity();
        List<String> productsAfter = shop.getProductsTitles();
        Assert.assertNotEquals(productsBefore, productsAfter);
    }

    @Test()
    public void ShopMenu_clickAverageRatingSort_viewPopularProduct() {
        List<String> productsBefore = shop.getProductsTitles();
        shop.selectSortByAverage();
        List<String> productsAfter = shop.getProductsTitles();
        Assert.assertNotEquals(productsBefore, productsAfter);
    }

    @Test()
    public void ShopMenu_clickNewnessRatingsSort_viewPopularProduct() {
        List<String> productsBefore = shop.getProductsTitles();
        shop.selectSortByNewness();
        List<String> productsAfter = shop.getProductsTitles();
        Assert.assertNotEquals(productsBefore, productsAfter);
    }

}
