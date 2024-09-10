package drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Log;


public class EdgeDriverManager extends DriverManagerBrowser {
    @Override
    public WebDriver getDriver(){
        Log.info("Launching edge browser...");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
