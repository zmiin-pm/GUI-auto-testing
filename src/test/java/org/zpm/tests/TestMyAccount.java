package org.zpm.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zpm.Pages.MyAccountPage;


public class TestMyAccount extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        String name = "gohehi9540@zaelmo.com";
        String password = "2020ItIsTooMuch!?";
        myAccount = new MyAccountPage().open().loginMyAccount(name, password);
    }

    @AfterMethod
    public void afterMethod() {
        myAccount.open().signOutMyAccount();
    }

    @Test
    public void HomePage_MyAccountClickAndSignInAndDashBoardClick_viewDashBoard() {
        Assert.assertTrue(myAccount
                .clickDashboard()
                .checkDashboardTextIsPresent());
    }

    @Test
    public void MyAccountPage_OrdersClick_viewOrders() {
        Assert.assertTrue(myAccount
                .clickOrders()
                .checkOrdersTableIsPresent());
    }

    @Test
    public void OrdersPage_ClickView_viewCustomerAndBilling() {
        Assert.assertTrue(myAccount
                .clickOrders()
                .clickOrdersViewButton()
                .checkOrderCustomerBillDetailsArePresent());
    }

    @Test
    public void OrdersPage_ClickView_viewOrderedDateAndStatus() {
        Assert.assertTrue(myAccount
                .clickOrders()
                .clickOrdersViewButton()
                .checkOrderNumberDateStatusArePresent());
    }
}
