package com.company;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
Each entry in read.txt file is separated with $$$$
StringIterator class opens the file and return each entry in turn as a String.
*/
public class StringIterator {


    public static void main( String[] args ) {


        Scanner input = new Scanner("read.txt");// Getting txt file
        String inputFile;

        inputFile = input.nextLine();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputFile);// txt file is in fileReader
        } catch (FileNotFoundException | NullPointerException e) {//If no file found
            e.printStackTrace();

            System.out.println("File does not exist or no file entered");
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String txtfile = "";


        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) { // while loop is running until next line is null
                if ( line.equals("$$$$")) {

                   line = bufferedReader.readLine(); // Getting a new line

                } else{
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    line = bufferedReader.readLine(); // Getting a new line


                }

            }
            txtfile = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {// May exception arise when finally try to close bufferedReader
            try {
                bufferedReader.close(); //bufferedReader class used as a resource class therefore we need close it
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    System.out.println(txtfile);
    }
}



