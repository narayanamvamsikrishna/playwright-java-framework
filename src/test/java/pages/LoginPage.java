package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;

    public LoginPage(Page page) {

        this.page = page;
    }

    private String userName = "#user-name";
    private String password = "#password";
    private String loginBtn = "#login-button";

    public void login(String user,String pwd) {

        page.fill(userName, user);

        page.fill(password, pwd);

        page.click(loginBtn);
    }
}