package org.zpm;

public class URLConstants {

    public static String BASE_URL;

    static {
        BASE_URL = System.getProperty("base_url", "http://practice.automationtesting.in/test-cases/");
    }

    public static String SHOP_PAGE = BASE_URL + "/shop/";
}
