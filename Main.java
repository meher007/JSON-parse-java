package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) {


        Scanner input = new Scanner(System.in);// Getting JSON file from command line
        String inputFile;
        System.out.print("Please enter file name: ");
        inputFile = input.nextLine();
        String fileName = inputFile;// Using filename later in the program
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
            //System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// May exception arise when finally try to close bufferedReader
            try {
                bufferedReader.close();// bufferedReader class used as a resource class therefore we need close it
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        JSONArray array = new JSONArray(json); // this will give you an array of all the nodes




        //System.out.println(array2.get(0));

        StringBuilder mysql = new StringBuilder("CREATE TABLE IF NOT EXISTS `USER` (\n" +
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

        mysql.append("INSERT INTO `USER`" +
                "\n\t(`Name`, `AlarmColor`, `Id`, `Key1`, `Value1`, `Key2`, `Value2`, `Key3`, `Value3`, `Key4`, `Value4`, `Key5`, `Value5`, `Key6`, `Value6`, `DatasourcesCount`,`_alertIcon`, `ElementCount`, `UniqueID`)" +
                "\n\tVALUES");

        for(int i = 0; i < array.length(); i++)
        {
            JSONObject jo = (JSONObject) array.get(i);
            mysql.append("\n\t('" + jo.get("Name") + "',");
            mysql.append(jo.get("AlarmColor") + ",");
            mysql.append(jo.get("Id") + ",");

            JSONArray array2 = (JSONArray) jo.get("Parameters");
            for (int j = 0; j < array2.length(); j++) {
                JSONObject jo2 = (JSONObject) array2.get(j);
                mysql.append("'" + jo2.get("Key") + "',");
                mysql.append("'" + jo2.get("Value") + "',");
            }
            int nullentries = 6 - array2.length();
            for (int j = 0; j < nullentries; j++) {
                mysql.append("'' ,"); // Entering null value because of inconsistency in 4th JSON object
                mysql.append("'' ,");
            }
            mysql.append(jo.get("DatasourcesCount") + ",");
            mysql.append("'" + jo.get("_alertIcon") + "',");
            mysql.append(jo.get("ElementCount") + ",");
            mysql.append("'" + jo.get("UniqueID") + "')");
            if(i < array.length() - 1)
                mysql.append(",");
        }

        mysql.append(";\n\n");

        System.out.print("Do you want " + fileName + " in a SQL statement(Y/N):");
        if(input.nextLine().equalsIgnoreCase("N")) {
            System.out.println(json);
        }
        else {
            System.out.print("Provide a file name for save SQL statement:");
            String name = input.nextLine();

            FileWriter fw = null;
            try {
                fw = new FileWriter(name, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter writer = new BufferedWriter(fw);
            try {
                writer.write(mysql.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Please check SQL statement in your resource repository" );


    System.out.println("Do you want to insert " + fileName + " in a Database(Y/N): ");
        
        if(input.nextLine().equalsIgnoreCase("Y")) {
            new InsertDB(json);// Passing json into InsertDB class through the constructor
        }
        else {
            System.out.println("Thank you for using our system");
        }

    }
}
