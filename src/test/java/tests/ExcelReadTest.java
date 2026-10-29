package tests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.ReportManager;
import base.BaseTest;
/*This test reads data from excel */


@Listeners(listeners.TestListener.class)
public class ExcelReadTest extends BaseTest {

        @Test
        public void readExcelData() throws Exception {

                String username = ExcelReader.getCellData(
                                "Sheet1",
                                1,
                                0);

                String password = ExcelReader.getCellData(
                                "Sheet1",
                                1,
                                1);

                ReportManager.info("username" + username);
                ReportManager.info("password" + password);

        }
}