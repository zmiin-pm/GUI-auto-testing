package org.zpm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.zpm.Driver.DriverHolder;
import org.zpm.URLConstants;

public class MyAccountPage extends AbstractPage {
    public MyAccountPage() {
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "input[name='login'][type='submit']")
    private WebElement loginSubmitButton;

    @FindBy(css = ".woocommerce-MyAccount-content > p > a")
    private WebElement signOutLink;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--dashboard > a")
    private WebElement dashboardLink;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--orders > a")
    private WebElement ordersLink;

    @FindBy(css = "td.order-actions > a")
    private WebElement ordersViewButton;

    @FindBy(css = ".woocommerce-MyAccount-content p:nth-child(2)")
    WebElement dashboardText;

    @FindBy(css = ".account-orders-table")
    WebElement ordersTable;

    @FindBy(css = ".order_details")
    WebElement ordersDetails;

    @FindBy(css = ".customer_details")
    WebElement customerDetails;

    @FindBy(css = "address")
    WebElement billingDetails;

    @FindBy(css = ".order-number")
    WebElement orderNumber;

    @FindBy(css = ".order-date")
    WebElement orderDate;

    @FindBy(css = ".order-status")
    WebElement orderStatus;


    public MyAccountPage loginMyAccount(String name, String password) {
//        waitForVisible(usernameInput);
        usernameInput.sendKeys(name);
//        waitForVisible(passwordInput);
        passwordInput.sendKeys(password);
//        waitForClick(loginSubmitButton);
        loginSubmitButton.click();
        return this;
    }

    public MyAccountPage signOutMyAccount() {
        signOutLink.click();
        return this;
    }

    public MyAccountPage clickDashboard() {
        dashboardLink.click();
        return this;
    }

    public MyAccountPage clickOrders() {
        ordersLink.click();
        return this;
    }

    public MyAccountPage clickOrdersViewButton() {
        ordersViewButton.click();
        return this;
    }

    public boolean checkDashboardTextIsPresent() {
        return dashboardText.getText().contains("From your account dashboard you can");
    }

    public boolean checkOrdersTableIsPresent() {
        ordersLink.click();
        waitForVisible(ordersTable);
        return ordersTable.isDisplayed();
    }

    public boolean checkOrderCustomerBillDetailsArePresent() {
        boolean orderDetailsArePresent = ordersDetails.isDisplayed();
        boolean customerDetailsArePresent = customerDetails.isDisplayed();
        boolean billingDetailsArePresent = billingDetails.isDisplayed();
        if (!orderDetailsArePresent) {
            System.out.println("Order Details Are not Present");
        }
        if (!customerDetailsArePresent) {
            System.out.println("Customer Details Are not Present");
        }
        if (!billingDetailsArePresent) {
            System.out.println("Billing Details Are not Present");
        }
        return (orderDetailsArePresent && customerDetailsArePresent && billingDetailsArePresent);
    }

    public boolean checkOrderNumberDateStatusArePresent() {
        boolean orderNumberIsPresent = orderNumber.isDisplayed();
        boolean orderDateIsPresent = orderDate.isDisplayed();
        boolean orderStatusIsPresent = orderStatus.isDisplayed();
        if (!orderNumberIsPresent) {
            System.out.println("Order Number is not Present");
        }
        if (!orderDateIsPresent) {
            System.out.println("Order Date is not Present");
        }
        if (!orderStatusIsPresent) {
            System.out.println("Order Status is not Present");
        }
        return (orderNumberIsPresent && orderDateIsPresent && orderStatusIsPresent);
    }

    public MyAccountPage open() {
        super.open(URLConstants.MYACCOUNT_PAGE);
        return this;
    }

    @Override
    public boolean atPage() {
        String currentURL = DriverHolder.INSTANCE.getDriver().getCurrentUrl();
        System.out.println(currentURL);
        return (currentURL.equals(URLConstants.MYACCOUNT_PAGE));
    }
}
