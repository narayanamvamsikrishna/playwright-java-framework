package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import base.BaseTest;
import utils.ConfigReader;

/* This is a sample test for Failure */

@Listeners(listeners.TestListener.class)
public class FailTest extends BaseTest {
    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class)
    public void verifyFailure(String browsers) {
        try (Playwright playwright = Playwright.create()) {
            ConfigReader cong = new ConfigReader();
            String URL = cong.get("GoogleURL");
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(false));
            Page page = browser.newPage();
            page.navigate(URL);
            Assert.assertEquals(page.title(), "Bing");
            // Assert.assertEquals(1, 1);
            browser.close();
        }
    }
}
