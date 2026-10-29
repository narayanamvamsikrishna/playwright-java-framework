package utils;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static String getCellData(
            String sheetName,
            int rowNum,
            int colNum) throws Exception {

        FileInputStream fis =
                new FileInputStream(
                        "src/test/resources/TestData/LoginData.xlsx");

        Workbook workbook =
                WorkbookFactory.create(fis);

        Sheet sheet =
                workbook.getSheet(sheetName);

        DataFormatter formatter =
                new DataFormatter();

        String value =
                formatter.formatCellValue(
                        sheet.getRow(rowNum)
                             .getCell(colNum));

        workbook.close();

        return value;
    }
}