package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "browsers")
    public Object[][] browsers() {

        return new Object[][]{

            {"chromium", "standard_user", "secret_sauce"},
            {"firefox", "standard_user", "secret_sauce"},
            {"webkit", "standard_user", "secret_sauce"}
        };
    }
}