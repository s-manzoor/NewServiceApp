package Common;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

// Using Excel file cell values to get Excel Sheet data. Flexibility issues with it. ExcelData class has more flexible solution.
// There is a ExcelSetting () method in LoginTest class to get Excel values and use them in Test methods
public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    public static void setExcelFile(String Path,String SheetName) throws Exception {

        try {

            // Open the Excel file

            FileInputStream ExcelFile = new FileInputStream(Path);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

        } catch (Exception e){

            throw (e);

        }

    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception{

        try{

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            String CellData = Cell.getStringCellValue();

            return CellData;

        }catch (Exception e){

            return"";

        }

    }

    public String excelEmail () throws Exception {
        ExcelUtils.setExcelFile("D:/Automation/IDSServiceAutomationPractice/src/main/resources/datafile/TestData.xlsx","Sheet1");

        String userEmail = ExcelUtils.getCellData(2,0);
        System.out.println("UserName is :" +userEmail);
        return userEmail;
    }

    public String excelPassword () throws Exception {
        ExcelUtils.setExcelFile("D:/Automation/IDSServiceAutomationPractice/src/main/resources/datafile/TestData.xlsx","Sheet1");

        String password = ExcelUtils.getCellData(2,1);
        System.out.println("Password is: " +password);
        return password;
        // invalidUser = ExcelUtils.getCellData(2,0);
    }

}
