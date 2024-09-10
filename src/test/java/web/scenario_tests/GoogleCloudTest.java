package web.scenario_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonReader;
import web.pages.GoogleCloudPage;
import web.setup.BaseTest;

import java.io.IOException;
import java.util.Set;

import static web.setup.Setup.configObject;

public class GoogleCloudTest extends BaseTest {
    private GoogleCloudPage googleCloudPage;

    @BeforeMethod
    public void navigateToHomePage() {
        googleCloudPage = new GoogleCloudPage(getSetup().getDriver());
        googleCloudPage.helper.navigateToUrl(configObject.getEnv());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        googleCloudPage.helper.clearCache();
    }

    @Test( testName = "Verify user can change the language", priority = 5)
    public void test1() throws IOException, InterruptedException {
        googleCloudPage
                .clickLanguageIcon()
                .clickDeutschLanguage();
        By heroTitle = By.xpath("//*[text()='"+DataTest.DeutschHeroTitle+"']");
        Assert.assertTrue(googleCloudPage.helper.isElementVisible(heroTitle), "The title is not matching");
    }

    @Test( testName = "Verify user can click on hyber link 'Get started for free' on hero section")
    public void test2() throws IOException, InterruptedException {
        googleCloudPage.clickBtnHeroSectionGetStartedForFree();
        // Get the current window handle (original tab)
        String originalWindow = getDriver().getWindowHandle();
        // Get all window handles
        Set<String> allWindowHandles = getDriver().getWindowHandles();

        // Switch to the new tab (there should be two windows open now)
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(googleCloudPage.helper.waitForURLContains("accounts.google.com"), "The url is not expected");
    }

    @Test( testName = "Verify user can search")
    public void test3() throws IOException, InterruptedException {
        googleCloudPage
                .clickSearchIcon()
                .enterTextSearchInput("testing")
                .selectOption("testing");
        By resultTitle = By.xpath("//*[text()='"+DataTest.ResulTitle+"']");
        Assert.assertTrue(googleCloudPage.helper.isElementVisible(resultTitle), "The title is not matching");
    }

    @Test( testName = "Verify user can click navigation bar")
    public void test4() throws IOException, InterruptedException {
        googleCloudPage.clickNavigation1();
        By topic1Result = By.xpath("(//*[text()='DIGITAL EVENT'])[1]");
        Assert.assertTrue(googleCloudPage.helper.isElementVisible(topic1Result), "The title is not matching");

        googleCloudPage.clickNavigation2();
        By topic2Result = By.xpath("(//*[text()='INNOVATORS PLUS'])[1]");
        Assert.assertTrue(googleCloudPage.helper.isElementVisible(topic2Result), "The title is not matching");

        googleCloudPage.clickNavigation3();
        By topic3Result = By.xpath("(//*[text()='SMALL AND MEDIUM BUSINESSES'])[1]");
        Assert.assertTrue(googleCloudPage.helper.isElementVisible(topic3Result), "The title is not matching");
    }

    @Test( testName = "Verify user can play the video")
    public void test5() throws IOException, InterruptedException {
        googleCloudPage.clickHeroVideo();
        googleCloudPage.helper.sleep(10);
        googleCloudPage.clickCloseButton();
    }
}
