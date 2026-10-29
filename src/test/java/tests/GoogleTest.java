package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import base.BaseTest;
import utils.CommonActions;
import utils.ConfigReader;

/* Basic google test */
@Listeners(listeners.TestListener.class)
public class GoogleTest extends BaseTest {

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class)
    public void verifyGoogleTitle(String browser) {
        CommonActions comm = new CommonActions(page);
        ConfigReader cong = new ConfigReader();
        String URL = cong.get("GoogleURL");
        comm.navigatetopage(URL, "Navigate to Google");

        String title = page.title();

        System.out.println(title);

        Assert.assertTrue(title.contains("Google"));
    }
}