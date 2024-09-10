package web.setup;

import drivermanager.DriverFactory;
import org.openqa.selenium.WebDriver;
import utils.JsonReader;

import java.io.IOException;

public class Setup {
    public WebDriver driver;
    public static JsonReader.ConfigObject configObject;
    public WebDriver getDriver() {
        return driver;
    }

    public void setupDriver() {
        configObject = JsonReader.configObject();
        String browserType = JsonReader.configObject().getBrowser();
        driver = DriverFactory.initDriver(browserType);
        driver.manage().window().maximize();
    }

    public void tearDownDriver() {
        driver.quit();
    }
}
