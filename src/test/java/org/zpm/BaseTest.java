package org.zpm;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.zpm.Driver.DriverHolder;
import org.zpm.Driver.DriverType;
import org.zpm.Pages.AbstractPage;
import org.zpm.Pages.HomePage;
import org.zpm.Pages.ShopPage;

public class BaseTest {

    protected static HomePage home;

    @BeforeSuite
    public void beforeSuite(){
        DriverHolder driverHolder = DriverHolder.INSTANCE;
        driverHolder.initDriver(DriverType.OPERA);
    }

    @BeforeMethod
    public void beforeTest(){
        ShopPage shop = new HomePage()
                .open()
                .clickShopMenuItem();
        home = shop.clickHomeLink();
    }


    @AfterSuite
    public void afterClass() {
        DriverHolder.INSTANCE.getDriver().quit();
    }
}
