package org.zpm.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.zpm.Driver.DriverHolder;
import org.zpm.Driver.DriverType;
import org.zpm.Pages.*;

public class BaseTest {

    protected static HomePage home;
    protected static ShopPage shop;
    protected static MyAccountPage myAccount;

    @BeforeSuite
    public void beforeSuite(){
        DriverHolder driverHolder = DriverHolder.INSTANCE;
        driverHolder.initDriver(DriverType.CHROME);
    }

    @AfterSuite
    public void afterClass() {
        DriverHolder.INSTANCE.getDriver().quit();
    }
}
