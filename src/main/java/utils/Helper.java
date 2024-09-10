package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Helper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public Helper(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    public void navigateToUrl(String URL) {
        driver.navigate().to(URL);
        waitForPageLoaded();
        waitForUrl(URL);
        Log.info("Navigate to "+URL+"");
    }

    public void waitForPageLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            Log.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                Log.info(error.getMessage());
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load.");
            }
        }
    }

    public void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        Log.info("Wait for url: " + url + "");
    }

    public void clickElement(By by) {
        try {
            waitForElementVisible(by).click();
            Log.info("Clicked on the element " + by.toString());
        } catch (StaleElementReferenceException e) {
            Log.error("The element goes stale, try to click again " + by.toString());
            waitForElementVisible(by).click();
        }
    }

    public WebElement waitForElementVisible(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error(error.getMessage());
            Log.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public boolean isElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Log.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            Log.error("The " + by + " is not visible");
            return false;
        }
    }

    public Boolean waitForURLContains(String str) {
        try {
            wait.until(ExpectedConditions.urlContains(str));
            return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public void enterText(By by, String value) {
        waitForElementVisible(by).sendKeys(value);
        Log.info("Set text " + value + " on " + by.toString());
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearCache() {
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    public void hover(By element) {
        actions.moveToElement(getWebElement(element)).perform();
        Log.info("Hover to: " + element + "");
    }

    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }
}
