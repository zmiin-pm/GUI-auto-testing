package org.zpm.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

/**
 * реализация через енум позволяет не создавать приватный контсруктор(он по умолчанию для енума)
 * и не нужен метод getInstance
 * подробнее преимущества такого способа реализации Singleton
 * https://dzone.com/articles/java-singletons-using-enum
 */

public enum DriverHolder {
    INSTANCE;
    private WebDriverWait webDriverWait;
    private WebDriver driver;

    public WebDriver initDriver(DriverType driverType) {
        driver = DriverFactory.setDriver(driverType);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
//        webDriverWait = new WebDriverWait(driver,10,100); // need to realise
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

}
