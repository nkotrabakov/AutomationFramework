package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='fc-button fc-cta-consent fc-primary-button']")
    private WebElement consentButton;

    @FindBy(css = "#menu-item-1311 > a")
    private WebElement signInButton;

    @FindBy(css = "#menu-item-1310 > a")
    private WebElement shopButton;

    @FindBy(css = "#menu-item-1314 > a")
    private WebElement username;

    public void cookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(consentButton));
        wait.until(ExpectedConditions.elementToBeClickable(consentButton));
        consentButton.click();
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void clickShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        shopButton.click();
    }

    public String getUsername() {
        return username.getText();
    }
}
