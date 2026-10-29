package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.ExcelReader;
import base.BaseTest;

@Listeners(listeners.TestListener.class)
public class ExcelReadTest extends BaseTest {

    @Test
    public void readExcelData() throws Exception {

        String username =
                ExcelReader.getCellData(
                        "Sheet1",
                        1,
                        0);

        String password =
                ExcelReader.getCellData(
                        "Sheet1",
                        1,
                        1);

        System.out.println(
                "Username : " + username);

        System.out.println(
                "Password : " + password);
    }
}