package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.Reporter;
import base.BaseTest;
import pages.LoginPage;
import utils.BrowserFactory;
import utils.DataProviders;

public class LoginTest extends BaseTest {

        @Test(dataProvider = "browsers")
        public void loginTest(
                        String browserName,
                        String username,
                        String password) {

                Reporter.getCurrentTestResult()
                                .setAttribute(
                                                "browser",
                                                browser);
                Browser browser = BrowserFactory.getBrowser(
                                playwright,
                                browserName);
                Page page = browser.newPage();
                page.navigate(
                                "https://www.saucedemo.com");

                LoginPage login = new LoginPage(page);

                login.login(
                                username,
                                password);

                System.out.println(
                                "Executed for : "
                                                + username);
        }
}