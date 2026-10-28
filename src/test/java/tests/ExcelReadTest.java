package tests;

import org.testng.annotations.Test;

import utils.ExcelReader;

public class ExcelReadTest {

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