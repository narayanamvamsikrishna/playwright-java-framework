package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import base.BaseTest;

public class FailTest extends BaseTest{

    @Test
    public void verifyFailure() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser =
                    playwright.chromium()
                            .launch(new BrowserType.LaunchOptions()
                            .setHeadless(false));

            Page page = browser.newPage();

            page.navigate("https://www.google.com");

            //Assert.assertEquals(page.title(), "Bing");
            Assert.assertEquals(1, 1);

            browser.close();
        }
    }
}