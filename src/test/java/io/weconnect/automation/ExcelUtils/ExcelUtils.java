package io.weconnect.automation.ExcelUtils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] GetExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = null;
        Workbook book = null;

        try {
            fis = new FileInputStream(filePath);

            // Determine the file type and initialize Workbook
            if (filePath.endsWith(".xlsx")) {
                book = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                book = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Invalid file type. Only .xls and .xlsx are supported.");
            }

            Sheet sheet = book.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            // Initialize a 2D Object array for test data
            Object[][] TestData = new Object[rowCount - 1][columnCount];

            for (int i = 1; i < rowCount; i++) { // Start from row 1 to skip headers
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    if (row.getCell(j) != null) {
                        TestData[i - 1][j] = row.getCell(j).toString();
                    } else {
                        TestData[i - 1][j] = "Empty"; 
                    }
                }
            }
            return TestData;

        } finally {
            if (book != null) {
                book.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
}
