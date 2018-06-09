package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) {


        Scanner input = new Scanner(System.in);// Getting JSON file from command line
        String inputFile;
        System.out.print("Please enter file name: ");
        inputFile = input.nextLine();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputFile);// JSON file is in fileReader
        } catch (FileNotFoundException | NullPointerException e) {//If no file entered or wrong file entered
            e.printStackTrace();

            System.out.println("File does not exist or no file entered");
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String json = "";


        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) { // while loop is running until next line is null
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine(); // Getting a new line
            }
            json = stringBuilder.toString();
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// May exception arise when finally try to close bufferedReader
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // The following third party library is used
        //https://github.com/stleary/JSON-java

        JSONArray array = new JSONArray(json); //Getting an array of entire JSON node


        StringBuilder mysql = new StringBuilder("CREATE TABLE IF NOT EXISTS `USER` (\n" + //SQL statement of table creating
                "\t`Name` varchar(256),\n" +
                "\t`AlarmColor` int(16),\n" +
                "\t`Id` int(12),\n" +
                "\t`Key1` varchar(256),\n" +
                "\t`Value1` varchar(256),\n" +
                "\t`Key2` varchar(256),\n" +
                "\t`Value2` varchar(256),\n" +
                "\t`Key3` varchar(256),\n" +
                "\t`Value3` varchar(256),\n" +
                "\t`Key4` varchar(256),\n" +
                "\t`Value4` varchar(256),\n" +
                "\t`Key5` varchar(256),\n" +
                "\t`Value5` varchar(256),\n" +
                "\t`Key6` varchar(256),\n" +
                "\t`Value6` varchar(256),\n" +
                "\t`DatasourcesCount` int(12),\n" +
                "\t`_alertIcon` varchar(256),\n" +
                "\t`ElementCount` int(12),\n" +
                "\t`UniqueID` varchar(256),\n" +
                "\tPRIMARY KEY  (`Id`)\n);\n\n");

        StringBuilder mysql2 = new StringBuilder("INSERT INTO `USER`" + "\n\t(`Name`, `AlarmColor`, `Id`, `Key1`, `Value1`, `Key2`, `Value2`, `Key3`, `Value3`, `Key4`, `Value4`, `Key5`, `Value5`, `Key6`, `Value6`, `DatasourcesCount`,`_alertIcon`, `ElementCount`, `UniqueID`)" + "\n\tVALUES");

        for (int i = 0; i < array.length(); i++) {

            JSONObject jo = (JSONObject) array.get(i);
            mysql2.append("\n\t('" + jo.get("Name") + "',");
            mysql2.append(jo.get("AlarmColor") + ",");
            mysql2.append(jo.get("Id") + ",");

            JSONArray array2 = (JSONArray) jo.get("Parameters");
            for (int j = 0; j < array2.length(); j++) {
                JSONObject jo2 = (JSONObject) array2.get(j);
                mysql2.append("'" + jo2.get("Key") + "',");
                mysql2.append("'" + jo2.get("Value") + "',");
            }
            //System.out.println("array2.length() is " + array2.length());
            int nullentries = 6 - array2.length();
            for (int j = 0; j < nullentries; j++) {
                mysql2.append("'' ,"); // Entering null value because of inconsistency in 4th JSON object
                mysql2.append("'' ,");
            }

            mysql2.append(jo.get("DatasourcesCount") + ",");
            mysql2.append("'" + jo.get("_alertIcon") + "',");
            mysql2.append(jo.get("ElementCount") + ",");
            mysql2.append("'" + jo.get("UniqueID") + "')");
            if (i < array.length() - 1) mysql2.append(",");
        }

        mysql2.append(";\n\n"); //SQL statement require a semicolons at the end

        //The following jdbc driver is used
        //https://dev.mysql.com/downloads/connector/j/
        try {
            //Configure MySQL ocnnection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poledb", "root", "root");

            Statement myStmt = myConn.createStatement();

            myStmt.executeUpdate(mysql.toString());
            myStmt.executeUpdate(mysql2.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}
