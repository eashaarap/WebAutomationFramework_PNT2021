package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LearnDataReader {
    /*
        Path/ Location to get to:
            - Absolute Path: Full Path: path only works on your computer
            C:\Users\Easha\IdeaProjects\PnTSession\DataTest
            -Relative Path:Path/Partial Path
             recommended to use, because can use this path for any computer
             DataTest/Sample.txt
     */

    public static void main(String[] args) throws IOException {

        LearnDataReader.readData();
    }

    public static void readData() throws IOException   {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null; //BufferedReader will help to read the data from when you send it
        //to when they receive it

        String filePath = "../PnTSession/DataTest/Sample.txt";
        try{
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String data;
            while((data=bufferedReader.readLine()) != null){
                System.out.println(data);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found Exception");
        }finally {
            fileReader.close();
            bufferedReader.close();
            System.out.println("File already closed");
        }


    }

}
