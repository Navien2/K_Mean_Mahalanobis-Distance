package com.navien.deprecated;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nawin
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadCSV{

 
    /**
     * Static utility method.
     * 
     * @param args
     * @throws IOException 
     */
    public static double[][] read(String theFile, int n_rows, boolean hasTitle) throws IOException {
        boolean firstTime = true;
    	double  [][] data=null;//=new double [n_rows][4]; //This will read n_rows rows
        File file=new File(theFile); // "C:/Users/nawin/Desktop/ColonNormData.csv"
         
        int row=0;
        int col=0;
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;
        if(hasTitle) {
        	line=reader.readLine();// this is correct, since the first line is not number 
        }
        while((line=reader.readLine())!=null && row<n_rows){
        	if(firstTime) {
         	   firstTime = false;
         	   StringTokenizer st=new StringTokenizer(line, ",");
         	   col = 0;
         	   while(st.hasMoreTokens()){
                    st.nextToken();
                    col++;
                }
                data=new double [n_rows][col];
                col = 0;
                continue;
            }
            StringTokenizer st=new StringTokenizer(line, ",");
            while(st.hasMoreTokens()){
                data[row][col]=Float.parseFloat(st.nextToken());
                col++;
            }
            col=0;
            row++;
        }
        return data;
    }
    
 }
    
   

