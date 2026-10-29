package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "browsers")
    public Object[][] browsers() {

        return new Object[][]{
            {"chromium"},
            {"firefox"},
            {"webkit"}
        };
    }
}