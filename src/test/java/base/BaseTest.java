package base;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.BrowserFactory;
import utils.ScreenshotUtil;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String browserName;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    @DataProvider(name = "browsers", parallel = true)
    public Object[][] browsers() {

        return new Object[][] {
                { "chromium" },
                { "firefox" },
                { "webkit" }
        };
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(
            @Optional("chromium") String browserName,
            Method method)
            throws Exception {

        this.browserName = browserName;

        playwright = Playwright.create();

        browser = BrowserFactory.getBrowser(
                playwright,
                browserName);

        context = browser.newContext();

        page = context.newPage();

        // Global wait settings
        page.setDefaultTimeout(60000);
        page.setDefaultNavigationTimeout(90000);
        page.waitForLoadState();

        ScreenshotUtil.capture(
                page,
                this.getClass().getSimpleName(),
                method.getName(),
                0,
                "BEFORE_TEST");
    }

    @AfterMethod
    public void tearDown(Method method) {

        try {

            if (page != null) {
                ScreenshotUtil.capture(
                        page,
                        this.getClass().getSimpleName(),
                        method.getName(),
                        0,
                        "AFTER_TEST");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    public Page getPage() {
        return page;
    }
}