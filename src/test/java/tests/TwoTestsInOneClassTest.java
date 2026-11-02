package tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.CommonActions;

/* This test navigates to Google and Amazon with robust step validations. */
@Listeners(listeners.TestListener.class)
public class TwoTestsInOneClassTest extends BaseTest {

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Google Test")
    public void googleTest(String browser) {
        // Initialize CommonActions with the current thread's page instance
        CommonActions actions = new CommonActions(page);

        // 1. Navigate to Google with reporting and a screenshot
        actions.navigatetopage("https://google.com", "Navigate to Google Home");

        // 2. Validate that the Google search input/logo is visible and wait for it
        String searchBoxLocator = "textarea[name='q']"; // Google's current search input locator
        actions.waitForVisible(searchBoxLocator);
        
        boolean isSearchBoxVisible = actions.isVisible(searchBoxLocator);
        Assert.assertTrue(isSearchBoxVisible, "Google search box should be visible on the page!");

        // 3. Optional: Perform a search action to test text entry and clicking
        actions.enterText(searchBoxLocator, "Playwright Java Automation", "Enter search term");
        actions.click("input[name='btnK']", "Click Google Search Button");
    }

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Get Amazon Test")
    public void amazonTest(String browser) {
        // Initialize CommonActions with the current thread's page instance
        CommonActions actions = new CommonActions(page);

        // 1. Navigate to Amazon with reporting and a screenshot
        actions.navigatetopage("https://www.amazon.in", "Navigate to Amazon Home");

        // 2. Validate that the Amazon search box is visible and wait for it
        String amazonSearchBox = "input#twotabsearchtextbox"; // Amazon's search input locator
        actions.waitForVisible(amazonSearchBox);
        
        boolean isAmazonSearchVisible = actions.isVisible(amazonSearchBox);
        Assert.assertTrue(isAmazonSearchVisible, "Amazon search box should be visible on the page!");

        // 3. Optional: Type into the search box
        actions.enterText(amazonSearchBox, "Laptop", "Enter product search in Amazon");
        actions.click("//a[contains(text(),\"Today's Deals\")]", "Click on Todays Deals");
        actions.click("//span[contains(text(),'Amazon Devices & Accessories')]", "Click Amazon devices");
        actions.click("//button[contains(text(),'Electronics')]","Clicked electronics");
        actions.selectRadio("//*[@id='departments-options']/span[2]/div[1]/label[1]/i[1]", "amazonDevices");
        actions.mouseOver("//span[contains(text(),'Account & Lists')]","Click the accounts n lists");
        actions.click("//span[contains(text(),'Your Account')]","Clicked on Your account");
        actions.click("//h2[contains(text(),'Login & security')]","Clicked on Login");
        actions.enterText("//input[@id='ap_email_login']","I dont have an account","Entered data in user id");
    
    }
}