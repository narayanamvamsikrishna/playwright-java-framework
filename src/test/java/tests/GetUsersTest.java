package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import base.BaseTest;

public class GetUsersTest extends BaseTest{

    @Test
    public void getUsers() {

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