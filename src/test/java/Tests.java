import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static ShopPage shopPage;
    static CartPage cartPage;
    static CheckoutPage checkoutPage;

    @BeforeClass
    public static void InitializeObjects() {
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        cartPage = new CartPage();
        shopPage = new ShopPage();
    }

    @Test
    public void testingAuthentication() {
        driver.get(Constants.URL);
        homePage.cookieConsent();
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUsername());
    }

    @Test
    public void testingAddingItemsToCart() {
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToSecondPage();
        shopPage.addItemToCart();
        assertEquals(Constants.CART_QUANTITY, shopPage.getNumberOfProducts());
    }

    @Test
    public void testingTheFullBuyingProcess() {
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToSecondPage();
        shopPage.addItemToCart();
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        assertEquals(Constants.ORDER_RECEIVED_MESSAGE, checkoutPage.getOrderStatus());
    }

    @AfterClass
    public static void closeObjects() {

        driver.close();
    }
}