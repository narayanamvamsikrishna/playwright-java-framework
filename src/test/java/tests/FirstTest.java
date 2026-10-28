package tests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.*;

import base.BaseTest;

public class FirstTest  extends BaseTest{
    @Listeners(listeners.TestListener.class)
public class FailTest extends BaseTest {
}
    @Test 
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(false));

            Page page = browser.newPage();

            page.navigate("https://google.com");

            System.out.println(page.title());

            browser.close();
        }
    }
}