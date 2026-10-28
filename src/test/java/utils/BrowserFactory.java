package utils;

import com.microsoft.playwright.*;

public class BrowserFactory {

    public static Browser getBrowser(Playwright playwright,
                                     String browserName) {

        Browser browser = null;

        switch (browserName.toLowerCase()) {

        case "firefox":
            browser = playwright.firefox()
                    .launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            break;

        case "webkit":
            browser = playwright.webkit()
                    .launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            break;

        default:
            browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
        }

        return browser;
    }
}