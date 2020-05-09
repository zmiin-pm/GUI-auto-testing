package org.zpm;

public class URLConstants {

    public static String BASE_URL;


    static {
        BASE_URL = System.getProperty("base_url", "http://practice.automationtesting.in/test-cases/");
    }

    public static String SHOP_PAGE = BASE_URL + "/shop/";
//    public static String ALERT_URL = BASE_URL + "/javascript_alerts";
//    public static String IFRAME_URL = BASE_URL + "/iframe";
//    public static String CONTEXT_URL = BASE_URL + "/context_menu";
//    public static String DRAG_URL = "http://www.pureexample.com/jquery-ui/basic-droppable.html";
}
