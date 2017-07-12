/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

// Fix the Issues
/**
 *
 * @author nwayyin
 */
/**
 * Review comment 1 - The structure of your project is weird. The structure
 * should be
 *
 * Root |- src | |- package | |- code |- git repo
 *
 * Right now you have a folder containing the repo and another folder containing
 * the source.
 *
 * Learn about how to setup your projects using maven and adopt the standard
 * directory layout model.
 */
public class CsvReader {

    /*
    Review comment 2 - Here you have no field variable backing `thefile`
    It is assigning a variable to itself and accomplishing nothing.

    The code is also poorly formatted. Use the auto format function in your IDE
    to fix it and remove unnecessary whitespace.
     */
    // field variable
    private String thefile;

    public CsvReader(String thefile) {

        thefile = thefile;
    }

// Default constructor
    public CsvReader() {

    }

    /**
     * Review comment 3 - Here you are throwing an exception which you have
     * passed from `readFile` Now no one is properly handling the exception. If
     * the file is not available, the program will crash, which is very bad.
     *
     * Somewhere along the line always handle your exeptions and take the
     * opportunity to deliver a meaningful error message. Your program should
     * never crash if you can avoid it.
     */
    public static void main(String[] args) throws IOException {

        // String thefile = "Data.csv"; // file path
        String thefile = "dataset3ID.csv";

        CsvReader reader = new CsvReader();

        // String content = reader.readFile(thefile, true);
        reader.readFile(thefile, true);

        List<Data> Data = reader.getData(thefile, true);

        System.out.println(Data);

    }

    /**
     *
     * @param theFile
     * @param hasTitle
     * @return
     * @throws IOException
     */
    public  String readFile(String theFile, boolean hasTitle) throws IOException {

        /**
         * Review comment 4 - Here you have trapped all the variables in this
         * scope. There is no way to access these variables outside of the
         * scope.
         *
         * Your code is also very poorly formatted.
         */
        String line;
        String result = " ";

        File file = new File(theFile);

        /**
         * Review comment 5 - More copy/paste work :( If you have a try/catch
         * block here
         *
         * why are you still throwing exception?
         */
        // try {
        /**
         * Review comment 6 - These resources are never closed. This creates a
         * memory leak in your code because inputstreams are never automatically
         * freed up in memory. This means that they will continue to use up
         * memory until the application is shut down.
         *
         * Read the documenation for FileReader and BufferedReader
         */
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);

        if (hasTitle) {
            line = buffReader.readLine();// this is correct, since the first line is not number
        }

        /**
         * Review comment 7 - x is never used.
         *
         * it is also only availabe inside of this scope
         */
        while ((line = buffReader.readLine()) != null) {

            /**
             * Review comment 8 - Here we have agreed that we are going to focus
             * on one problem at a time. Your methods are too long. Ideally they
             * should only focus on doing 1 thing and do them well.
             *
             * Methods that are too long become very messy and very hard to
             * manage and extremely difficult to test.
             *
             * If you have a method which does more than 1 thing it is TOO LONG.
             *
             * break this up into 1. get text from file 2. Get list of lines
             * from text 3. parse list of lines from text 4. return data
             */
            StringTokenizer st = new StringTokenizer(line, ",");
            System.out.println("Number of tokens: " + st.countTokens());

            while (st.hasMoreTokens()) {
                // System.out.println("Token: " + st.nextToken());
                result = st.nextToken();
                System.out.println(result);

            }
        }

        // } catch (IOException e) {
        // System.exit(0);
        // }
        /**
         * Review comment 9 - You are returning a value that has nothing to do
         * with the data you have parsed.
         */
        return result;
    }

   static public List<Data> getData(String thefile, boolean hasTitle) throws IOException {

        ArrayList<Data> result = new ArrayList();

        File aFile = new File(thefile);

        String aLine = null;

        BufferedReader reader = new BufferedReader(new FileReader(aFile));

        if (hasTitle) {
            aLine = reader.readLine();// this is correct, since the first line is not number
        }

        while ((aLine = reader.readLine()) != null) {

            Data line = Data.fromString(aLine);

            result.add(line);
        }
        reader.close();

        return result;
    }

}
