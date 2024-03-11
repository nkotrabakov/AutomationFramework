import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

public class Main {
    public static void main(String[] args) throws Exception {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech");

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        homePage.cookieConsent();
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));

        if (homePage.getUsername().equals("Hello, New")) {
            System.out.println("Test passed");
        }
        else {
            System.out.println("Test failed");
        }

        homePage.clickShopButton();

        shopPage.addItemToCart();
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        checkoutPage.getOrderStatus();

        if (checkoutPage.getOrderStatus().equals("Order received")) {
            System.out.println("Test passed");
        }
        else {
            throw new Exception();
        }

        DriverSingleton.closeObjectInstance();
    }
}
