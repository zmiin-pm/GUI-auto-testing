package org.zpm;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zpm.Pages.HomePage;
import org.zpm.Pages.ProductPage;
import org.zpm.Pages.ShopPage;


public class Tests extends BaseTest {

    @BeforeMethod
    public void beforeTest(){
        ShopPage shop = new HomePage()
                .open()
                .clickShopMenuItem();
        home = shop.clickHomeLink();
    }

    @Test()
    public void ShouldOpenHomePageWithThreeSlidersOnly() {
        Assert.assertTrue(home.countArrivals() == 3);
    }

    @Test(priority = 1)
    public void ShouldOpenNextPageByClickOnImage() {
        ProductPage productPage = home.clickOnProduct();
        Assert.assertTrue(productPage.atPage());
    }

    @Test(priority = 2)
    public void ShouldHaveDescriptionTextOnTab() {
        ProductPage productPage = home.clickOnProduct();
        Assert.assertTrue(productPage.clickOnDescriptionTab().checkForDescription());
    }

    @Test(priority = 3)
    public void ShouldHaveReviewersTextOnTab() {
        ProductPage productPage = home.clickOnProduct();
        Assert.assertTrue(productPage.clickOnReviewsTab().checkForReviews());
    }

    @Test(priority = 4)
    public void ShouldAddBooksToTheBasket() {
        ProductPage productPage = home.clickOnProduct();
        Integer numberBefore = productPage.getBasketItemsNumber();
        productPage.addToBasket();
        Integer numberAfter = productPage.getBasketItemsNumber();
        Assert.assertTrue((numberAfter - numberBefore) == 1);
    }

    @Test(priority = 5)
    public void ShouldTrowError() {
        ProductPage productPage = home.clickOnProduct();
        Integer numberBefore = productPage.getBasketItemsNumber();
        productPage.setUnreachableNumberOfItems();
        productPage.addToBasket();
        Integer numberAfter = productPage.getBasketItemsNumber();
        Assert.assertTrue(numberAfter == numberBefore); // test must catch an error prompt
    }
}
