package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.Reporter;
import base.BaseTest;
import pages.LoginPage;
import utils.BrowserFactory;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.ReportManager;
import utils.ScreenshotUtil;
import utils.CommonActions;

import org.testng.annotations.DataProvider;
import utils.ExcelReader;

import org.testng.annotations.Listeners;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {

        @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class)
        public void loginTest(String browserName) throws Exception {
                ConfigReader cong=new ConfigReader();
                CommonActions comm=new CommonActions(page);
                String URL=cong.get("url");
                String username = ExcelReader.getCellData("Sheet1", 1, 0);

                String password = ExcelReader.getCellData(
                                "Sheet1",
                                1,
                                1);
                ReportManager.info("username" + username);
                ReportManager.info(" Password" + password);

                //ScreenshotUtil.captureAndAttach(page, "Click Login- test");

                comm.navigatetopage(URL, "Navigate to Google Home Page");
                //ScreenshotUtil.captureAndAttach(page, "Opened login page - test");

                // login using username/password

                LoginPage login = new LoginPage(page);
                // String username = ExcelReader.getCellData("Sheet1", 1, 0);

                // String password = ExcelReader.getCellData("Sheet1", 1, 1);
                login.login(username, password);
                //ScreenshotUtil.captureAndAttach(page, "Logged in - test");

                ReportManager.pass(" Successfully logged in");
        }
}