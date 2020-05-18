package org.zpm.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.zpm.Driver.DriverHolder;
import org.zpm.Driver.DriverType;
import org.zpm.Pages.HomePage;
import org.zpm.Pages.MyAccountPage;
import org.zpm.Pages.ShopPage;
import org.zpm.utils.Listeners.TestListener;


@Listeners({TestListener.class})
public class BaseTest {

    protected static HomePage home;
    protected static ShopPage shop;
    protected static MyAccountPage myAccount;

    @BeforeSuite
    public void beforeSuite() {
        DriverHolder.INSTANCE.initDriver(
                DriverType.CHROME,
                0,  // implicitly
                0); // explicitly
    }

    @AfterSuite
    public void afterClass() {
        DriverHolder.INSTANCE.getDriver().quit();
    }
}
