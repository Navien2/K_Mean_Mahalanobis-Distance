package com.navien.kmeanmahalanobis;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nwayyin
 */
public class Data {

    //the design of Data object is according to the CSV file content we can not put k number
       
   public  String ID;
    
   public double D1;
    
   public  double D2;

    //Method1 
    // "F12","1.2","2.2"
    // "F12,1.2,2.2"
    @Override
    public String toString() {
        return "" + ID + "," + D1 + "," + D2;  // ?
    }

    //Method2
    public static String getHeader() {
        return " ID " + "," + "X1" + "," + "X2";
    }

    public static Data fromString(String data) {

        String[] DataSpliter = data.split(",");   // "F12,1.2,2.2"

        Data fileData = new Data();

        fileData.ID = DataSpliter[0];
        fileData.D1 = Double.parseDouble(DataSpliter[1]);
        fileData.D2 = Double.parseDouble(DataSpliter[2]);

        return fileData;
    }    

}
