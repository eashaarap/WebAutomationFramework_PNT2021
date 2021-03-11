package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {

    /*
       1st step: Write: want to create Excel file using java
        2nd step: Insert data to the created Excel file
     */
    //File Path
    public static final String FILE_PATH = "../PnTSession/DataTest/SampleExelFile.xlsx";

    public static void main(String[] args) throws IOException {
        Object[] [] stDetails = {
                { "sl", "FirstName", "LastName", "Phone", "Address"},
                { "101", "Easha", "Khanam", "5176543322", "Albany,NY"},
                { "102", "Dazima", "Sherpa", "5183219988", "Jackson, NJ"},
                { "103", "Sam", "Martin", "7186543322", "Houston,TX"},
                { "104", "Israt", "Reto", "5596543322", "Syracuse,CA"},
        };
        WriteExcelFile.writeExcel(FILE_PATH, "pnt", stDetails);
    }

    public static void writeExcel(String filePath, String sheetName, Object[][] data)throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowNum = 0;
        System.out.println("Excel file Created");

        for (Object[] dt : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : dt) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
        } catch(FileNotFoundException fn) {
            System.out.println("File not found Exception");
        } catch(IOException io){
            System.out.println("Done");
        }
    }
}
