import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton() {
        instantiate("Chrome");
    }

    private WebDriver instantiate(String strategy) {
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static DriverSingleton getInstance() {
        if (instance == null) {
            instance = new DriverSingleton();
        }

        return instance;
    }

    public static void closeObjectInstance() {
        instance = null;
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
