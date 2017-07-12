/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nwayyin
 */
public class ReadCsvFiles {
    
    static public double[][] getData(String thefile, boolean hasTitle) throws IOException {

        ArrayList result = new ArrayList();
        File aFile = new File(thefile);
        String aLine = null;

        BufferedReader reader = new BufferedReader(new FileReader(aFile));
        
        if(hasTitle) {
        aLine=reader.readLine();// this is correct, since the first line is not number 
        }
        
        
        
        while ((aLine = reader.readLine()) != null) {
            String[] DataSpliter = aLine.split(",");
            
            // we do not need the string tokenizer to will repreat it "F12 1.2 1.3"
            //StringTokenizer st = new StringTokenizer(aLine, ",");  

            //while (st.hasMoreTokens()) {
                for (int i = 0; i < DataSpliter.length; i++) {
                    result.add(DataSpliter[i]);
                   // st.nextToken();
                //}
           }

        }
        reader.close();

        String[] OneDarray = new String[result.size()];
        result.toArray(OneDarray);

        int k = 0;
        int row = OneDarray.length / 3;
        int col = 3;
        String[][] data = new String[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                data[i][j] = OneDarray[(i * col) + j];
            }
        }

        double[][] DataFile = new double[data.length][data[0].length];

        for (int ii = 0; ii < data.length; ii++) {
            for (int jj = 0; jj < data[0].length; jj++) {

                NumberFormat nf = NumberFormat.getInstance();
                try {
                    DataFile[ii][jj] = nf.parse(data[ii][jj]).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(ReadCsvFiles.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return DataFile;
    }

    
}
