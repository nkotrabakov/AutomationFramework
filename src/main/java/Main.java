import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        DriverSingleton driverSingleton = DriverSingleton.getInstance();
        WebDriver driver = driverSingleton.getDriver();
        driver.get("https://bitheap.tech");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cookieAccept = driver.findElement(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']"));
        wait.until(ExpectedConditions.visibilityOf(cookieAccept));
        wait.until(ExpectedConditions.elementToBeClickable(cookieAccept));
        cookieAccept.click();
    }
}
