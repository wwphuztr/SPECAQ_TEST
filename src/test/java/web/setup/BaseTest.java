package web.setup;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import java.awt.*;
import java.io.IOException;

public class BaseTest {
    public static ThreadLocal<Setup> setup = new ThreadLocal<>();
    //public ScreenRecoderHelpers screenRecoderHelpers;

    public Setup getSetup() {
        return setup.get();
    }
    public WebDriver getDriver() {
        return getSetup().driver;
    }
    @BeforeSuite
    public void beforeSuite() {

    }

    @BeforeClass
    public void initTestSetup(@Optional("theme1") String theme) throws AWTException, IOException {
        //screenRecoderHelpers = new ScreenRecoderHelpers();
        setup.set(new Setup());
        getSetup().setupDriver();
        //HelperListener.setDriver(getDriver(), platform, theme);
    }

    @AfterClass
    public void endSession() {
        getSetup().tearDownDriver();
    }
}
