package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import base.BaseTest;
        
@Listeners(listeners.TestListener.class)
public class GetUsersTest extends BaseTest{

    @Test(dataProvider = "browsers",
      dataProviderClass = utils.DataProviders.class)

    public void getUsers( String browser) {

        try (Playwright playwright = Playwright.create()) {

            APIRequestContext request =
                    playwright.request().newContext();

            APIResponse response =
                    request.get(
                            "https://reqres.in/api/users/2");

            System.out.println(response.status());

            Assert.assertEquals(
                    response.status(),
                    200);
        }
    }
}