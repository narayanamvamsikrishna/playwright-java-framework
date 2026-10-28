package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {

        page.navigate("https://www.google.com");

        String title = page.title();

        System.out.println(title);

        Assert.assertTrue(title.contains("Google"));
    }
}