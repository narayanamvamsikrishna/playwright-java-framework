package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import base.BaseTest;
import utils.ConfigReader;
import utils.ReportManager;
import utils.ApiUtils; // Using ApiUtils for payload management
import utils.CommonActions;
/*This test does a Get, Post and PUT for Rest based api calls */

@Listeners(listeners.TestListener.class)
public class RESTAction extends BaseTest {

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Get User API Test")
    public void getUsersTest(String browser) {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext();
            ConfigReader cong = new ConfigReader();
            String apiUrl = cong.get("createURL"); // e.g., https://reqres.in/api/users
            ReportManager.info("Sending GET request to fetch user details...");
            APIResponse response = request.get(apiUrl);
            int statusCode = response.status();
            ReportManager.info("GET Response Status Code: " + statusCode);
            Assert.assertEquals(statusCode, 200, "User retrieval failed!");
            ReportManager.pass("GET request completed successfully with status 200.");
        } catch (Exception e) {
            ReportManager.fail("Test failed due to exception: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Create User API Test")
    public void createUserTest(String browser) {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext();
            ConfigReader cong = new ConfigReader();
            String apiUrl = cong.get("createURL");
            // Load payload using ApiUtils helper class
            String jsonPayload = ApiUtils.loadAndParametrizedPayload("createUserPayload.json", "createUser");
            ReportManager.info("Sending POST request to create a user with payload: " + jsonPayload);
            APIResponse response = request.post(apiUrl,
                    RequestOptions.create().setData(jsonPayload));
            int statusCode = response.status();
            String responseBody = response.text();
            ReportManager.info("POST Response Status Code: " + statusCode);
            ReportManager.info("POST Response Body: " + responseBody);
            Assert.assertEquals(statusCode, 201, "User creation failed!");
            Assert.assertTrue(responseBody.contains("morpheus"),
                    "Response body should contain the created user's name!");
            ReportManager.pass("POST request completed successfully with status 201.");
        } catch (Exception e) {
            ReportManager.fail("Test failed due to exception: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Update User API Test")
    public void updateUserTest(String browser) {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext();
            ConfigReader cong = new ConfigReader();
            String apiUrl = cong.get("createURL");
            // Load payload using ApiUtils helper class
            String jsonPayload = ApiUtils.loadAndParametrizedPayload("updateUserPayload.json", "updateUser");
            ReportManager.info("Sending PUT request to update user ID 2 with payload: " + jsonPayload);
            APIResponse response = request.put(apiUrl + "/2",
            RequestOptions.create().setData(jsonPayload));
            int statusCode = response.status();
            String responseBody = response.text();
            ReportManager.info("PUT Response Status Code: " + statusCode);
            ReportManager.info("PUT Response Body: " + responseBody);
            Assert.assertEquals(statusCode, 200, "User update failed!");
            Assert.assertTrue(responseBody.contains("zion resident"),
                 "Response body should show the updated job title!");
            ReportManager.pass("PUT request completed successfully with status 200.");
        } catch (Exception e) {
            ReportManager.fail("Test failed due to exception: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}