package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Helper;
import web.setup.Setup;

import java.time.Duration;

public class GoogleCloudPage extends Setup {
    public Helper helper;
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private SoftAssert softAssert;

    public GoogleCloudPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By iconLanguage = By.xpath("//header//i[contains(@class, 'google-material-icons') and text()='language']/..");
    private By DeutschLanguage = By.xpath("(//ul//*[contains(text(), 'Deutsch')])[1]/../..");
    private By btnHeroSectionGetStartedForFree = By.xpath("//section[@track-metadata-module='home hero']//a[@track-name='get started for free']");
    private By searchIcon = By.xpath("(//*[text()='Contact Us'])[1]/following::*[text()][1]/../../..");
    private By txtSearch = By.xpath("//input[@aria-label='Search']");
    private By tabNavigation1 = By.xpath("//*[@track-type='tab'][1]");
    private By tabNavigation2 = By.xpath("//*[@track-type='tab'][2]");
    private By tabNavigation3 = By.xpath("//*[@track-type='tab'][3]");
    private By heroVideo = By.xpath("(//a[contains(@href, 'youtube')])[1]");
    private By videoSection = By.xpath("//video");
    private By iconClose = By.xpath("//button[@aria-label='Close modal']//span");

    public GoogleCloudPage hoverVideo() {
        helper.hover(videoSection);
        return this;
    }

    public GoogleCloudPage clickCloseButton() {
        helper.clickElement(iconClose);
        return this;
    }

    public GoogleCloudPage clickHeroVideo() {
        helper.clickElement(heroVideo);
        helper.waitForElementVisible(iconClose);
        return this;
    }

    public GoogleCloudPage clickLanguageIcon() {
        helper.clickElement(iconLanguage);
        return this;
    }

    public GoogleCloudPage clickDeutschLanguage() {
        helper.clickElement(DeutschLanguage);
        return this;
    }

    public GoogleCloudPage clickBtnHeroSectionGetStartedForFree() {
        helper.clickElement(btnHeroSectionGetStartedForFree);
        return this;
    }

    public GoogleCloudPage clickSearchIcon() {
        helper.clickElement(searchIcon);
        return this;
    }

    public GoogleCloudPage enterTextSearchInput(String keyword) {
        helper.enterText(txtSearch, keyword);
        return this;
    }

    public GoogleCloudPage selectOption(String option) {
        helper.clickElement(By.xpath("//li//*[text()='"+option+"']//ancestor::li"));
        return this;
    }

    public GoogleCloudPage clickNavigation1() {
        helper.clickElement(tabNavigation1);
        return this;
    }

    public GoogleCloudPage clickNavigation2() {
        helper.clickElement(tabNavigation2);
        return this;
    }

    public GoogleCloudPage clickNavigation3() {
        helper.clickElement(tabNavigation3);
        return this;
    }
}
