package databases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConnectDatabase {

    //Secret.properties file
    //jdbc driver
    //jdbc url
    //config jdbc
    //mySql Query
    /*
    Steps:
        Secret.properties file ----> loaded in java using loadProperties method
        ConnectDatabase() method ----> develop connect to mySQL server using url, userName, Password
        readDatabase()----> call connectDatabase() method
                            columnName hold in a ListArray
                            use for each loop to iterate value
     */

    public static Connection connection = null;
    public static Statement statement = null;
    public static PreparedStatement preparedStatement= null;
    public static ResultSet resultSet=null;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        //ConnectDatabase.connectToSQLDatabase();

        //ConnectDatabase.readDatabase("movie", "title");

      //ConnectDatabase.insertDataFromStringToSQLTable("1999","movie","releaseYear");

//      int [] numbers = {233, 242, 253, 262, 274, 228, 293, 330, 321};
//        ConnectDatabase.insertDataFromArrayToSqlTable(numbers, "productDetails", "productID");

//        List<String> stNames = new ArrayList<>();
//                stNames.add("Duck");
//                stNames.add("Bob");
//                stNames.add("Bablu");
//                stNames.add("Zahra");
//                stNames.add("Billy");
//                stNames.add("Samantha");
//
//        ConnectDatabase.insertDataFromArrayListToSqlTable(stNames,"students","studentNames");


        //ConnectDatabase.createPropertyFile();

        ConnectDatabase.insertProfileToSqlTable("movie","title","releaseYear","Hidden Figures","2000");
    }

    // to create our secret.properties file we can create a method
    public static Properties createPropertyFile() throws IOException {
        Properties propFile = new Properties();
        FileOutputStream outpt = new FileOutputStream("myNewSecretFile.properties");
        propFile.setProperty("url","jdbc:mysql://localhost:3306/pnt_selenium?serverTimezone=UTC");
        propFile.setProperty("userName","root");
        propFile.setProperty("password","password");

        propFile.store(outpt, null);
        return propFile;
    }
    //Load Properties: allows us to use data stored in another file i.e secret.properties
    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(filePath);
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
    //Database Connection
    public static Connection connectToSQLDatabase() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = ConnectDatabase.loadProperties(filePath);
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String user = prop.getProperty("MYSQLJDBC.userName");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        System.out.println("Database is Connected successfully");
        return connection;
    }
    public static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //This method is just getting the result
    //returning List type parameter of String type hence: List<String>
    //returning List type array: of String data type
    //Resultset: take resultset type value from user
    public static List<String> getResultSetData(ResultSet resultSet, String columnName) throws SQLException {
        List<String> dataList = new ArrayList<>();
        //while loop will help us get the data from the columns by iterating
        //using dataList.add to make it dynamic by passing parameter
        while (resultSet.next()) {//if  your resultset has a value then go to the next value
            String itemName = resultSet.getString(columnName);//it will get the value from the columnName as a String
            dataList.add(itemName);//then we add that value in our List
        }
        return dataList;//returning dataList because our goal is to see the list
    }
    //This method is reading the data; now we will use previous method in new method to read the columns
    public static List<String> readDatabase(String tableName, String columnName) throws SQLException {
        List<String> data = new ArrayList<>();
        try {
            ConnectDatabase.connectToSQLDatabase();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from " + tableName);
            data = ConnectDatabase.getResultSetData(resultSet, columnName);
            for (String dt : data) {
                System.out.println(dt + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.close();
        }
        return data;
    }
    public static String filePath="../PntSession/ExtLibrary/Properties/Secret.properties";




    public static void insertProfileToSqlTable(String tableName,String columnName1, String columnName2, String name, String num) throws SQLException, IOException, ClassNotFoundException {
        connectToSQLDatabase();
        preparedStatement =
                connection.prepareStatement("INSERT INTO " + tableName + " ( " + columnName1 + "," +columnName2 + ") VALUES(?,?);");
        preparedStatement.setObject(1, name);
        preparedStatement.setObject(2, num);
        preparedStatement.executeUpdate();


    }
    public static void insertDataFromStringToSQLTable(String data, String tableName, String columnName) {
        try {
            //first we need to connect to the database in order to read anything from the database
            connectToSQLDatabase();

            //to prepare your query to get the data as you would in mysql;  //? means unknown, you dont know it, as we
            // will get it from the user,also means one column, if you want multiple column add more question marks
            // like ?,?
            preparedStatement = connection.prepareStatement("Insert into " + tableName + " (" + columnName + ") values(?)");

            //now we have our query ready we are setting the value: mysql index starts at 1
            preparedStatement.setString(1, data);//data is the value that will go in column

            //now to execute query
            preparedStatement.executeUpdate();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (ClassNotFoundException cf) {
            cf.printStackTrace();
        }
    }
/*
SUDO CODE:
    check if table exist
    if it exist drop table
    then create table
    then insert data
 */
    //2nd approach using Arrays: of int type of data
    public static void insertDataFromArrayToSqlTable(int[] arrayData, String tableName, String columnName) {
        try {
            //connect and prepare your query as we did in the last method
            ConnectDatabase.connectToSQLDatabase();

            //1st STEP:if table exist drop table
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS `" + tableName + "`");
            //if there is this table name then delete it
            preparedStatement.executeUpdate();

            //2nd STEP: create a new table
            // explanation for query:
            /* int(11): inside the bracket is the display width; amount of characters you want to appear
            'big int' data ype used in sql when we want to store integer values bigger than normal int range
             */
            preparedStatement = connection.prepareStatement("CREATE TABLE `" + tableName + "`(`ID` int (11) not null AUTO_INCREMENT,`" + columnName + "` bigint(20) DEFAULT NULL, PRIMARY KEY (`ID`));");
            preparedStatement.executeUpdate();

            //3rd STEP:  insert data in our new table: must use for loop to get data from arrays
            for (int i = 0; i < arrayData.length; i++) {
                // SQL Query: insert into tableName (columnName) values();
                preparedStatement = connection.prepareStatement("INSERT  INTO " + tableName + " (" + columnName + ") values (?)");
                preparedStatement.setObject(1, arrayData[i]);// as we did in previous method, column 1 + array data
                preparedStatement.executeUpdate();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    //this is the same as before but now we will be using ArrayList and data type of String
    public static void insertDataFromArrayListToSqlTable(List<String> list, String tableName,String columnName) throws SQLException, IOException, ClassNotFoundException {
        connectToSQLDatabase();
        preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS `" + tableName + "`");
        preparedStatement.executeUpdate();

        //use VARCHAR instead of int for our column value
        preparedStatement = connection.prepareStatement("CREATE TABLE `" + tableName + "`(`ID` int (11) not null AUTO_INCREMENT,`" + columnName + "` VARCHAR(20) DEFAULT NULL, PRIMARY KEY (`ID`));");
        preparedStatement.executeUpdate();

        for(String st: list) {
            preparedStatement = connection.prepareStatement("INSERT  INTO " + tableName + " (" + columnName + ") values (?)");
            preparedStatement.setObject(1, st);//using setObject as its a ArrayList; number of parameters is 1 hence
            preparedStatement.executeUpdate();
        }
    }

}
