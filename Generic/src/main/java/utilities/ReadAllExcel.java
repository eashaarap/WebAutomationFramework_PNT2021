package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadAllExcel {

    /*
        Get all values from Excel
     */

    //1st step
    public static String filePath = "../PnTSession/DataTest/pnt_class.xlsx";

    public static void main(String[] args) throws IOException {

        readExcel(filePath,"Sheet1");

    }

    //2nd step
    public static void readExcel(String path,  String sheetName) throws IOException {
        //Create path for excel, and pass parameter 'path'
        FileInputStream input = new FileInputStream(path);

        //to open the path:
        Workbook workbook = WorkbookFactory.create(input);

        //find excel:
        Sheet sheet = workbook.getSheet(sheetName);

        //use loop to iterate through the table:
        //first loop will loop through the Rows
        for(Row row: sheet){

            //second loop will loop through the Cells
            for(Cell cell: row){

                //now we need to evaluate the value within the cells; you can use if els statement
                switch(cell.getCellType()){//getCellType gets value inside the cellsg
                    case NUMERIC:
                        System.out.print((int)cell.getNumericCellValue()+ "\t");//print the value you are getting
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue()+ "\t");
                        break;
                    case BLANK:
                        System.out.print("Blank" + "\t");
                        break;

                }

            }
            //add a line to it loops to the next line
            System.out.println();
        }//end of loop

        input.close();
    }//end of method
}//end of class
