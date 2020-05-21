package org.zpm.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

/**
 * Pattern Singleton using Enum
 * реализация через енум позволяет не создавать приватный контсруктор(он по умолчанию для енума)
 * и не нужен метод getInstance
 * подробнее преимущества такого способа реализации Singleton
 * https://dzone.com/articles/java-singletons-using-enum
 */

public enum DriverHolder {
    INSTANCE;
    private WebDriverWait webDriverWait; // Explicit waits
    private WebDriver driver;

    // Method for driver initialization.
    // Use enum DriverType and PageFactory to init one of Chrome, Opera or Firefox Webdriver
    // Also explicit and implicit waits are defining here

    public WebDriver initDriver(DriverType driverType, int implicitlyWait, int explicitlyWait) {
        driver = DriverFactory.setDriver(driverType);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);  // implicit waits work always when you use FindElements
        webDriverWait = new WebDriverWait(driver,explicitlyWait,explicitlyWait/2*10); // need to use in case singlePage app
        driver.manage().window().maximize();                                         // or for wait element to be clickable or smth
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

}
