package pages;

import com.microsoft.playwright.Page;

import utils.CommonActions;
import utils.ScreenshotUtil;

public class LoginPage {

    Page page;
    private CommonActions actions;

    public LoginPage(Page page) {

        this.page = page;
        this.actions = new CommonActions(page);

    }

    private String userName = "#user-name";
    private String password = "#password";
    private String loginBtn = "#login-button";

    public void login(String user, String pwd) {
        actions.enterText(userName, user, "Enter data in to Login");
        //ScreenshotUtil.captureAndAttach(page, "Login entered");

        // page.fill(userName, user);
        actions.enterText(password, pwd, "Enter data in to Password");
        //ScreenshotUtil.captureAndAttach(page, "PW Entered");

        actions.click(loginBtn, "Clicked login button");
        // page.fill(password, pwd);
        //ScreenshotUtil.captureAndAttach(page, "Logged in");
        // page.click(loginBtn);
    }
}