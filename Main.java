/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author navien
 */
public class Main {
    
    
    
  public static void main(String[] args) throws IOException {

        // String thefile = "Data.csv"; // file path
        String thefile = "c:\\users\\navien\\Desktop\\dataTest\\dataset3Id2.csv";

        CsvReader reader = new CsvReader();

        // String content = reader.readFile(thefile, true);
        reader.readFile(thefile, true);

        List<Data> dataFile = reader.getData(thefile, true);
        
         double[][] data = new double[dataFile.size()][];

            String[] Id = new String[dataFile.size()];
            
            
        
       for (int i = 0; i < dataFile.size(); i++) {
                data[i] = new double[]{dataFile.get(i).D1, dataFile.get(i).D2};

                Id[i] = dataFile.get(i).ID;
            }

        //System.out.println(data);
        
        int clusterNumber=2;
        
        int selectedType=1;
        int p =2; 
        int Dim= 2; 
        
        
          float threshold = 0.001f;
            //
            // Kmean1 kMean = new Kmean1(rawData, threshold, clusterNumber, selectedType, p, Dim);
             Kmean1 kMean = new Kmean1(data, threshold, clusterNumber, selectedType, p, Dim, Id);

             kMean.plotDataset(data);
             
             
              KMeanChart chart = new KMeanChart("KMean Algorithm", kMean);
               
              chart.plotKMeansResult();
              chart.pack();
              chart.setVisible(true);

            KMeansResult result = kMean.calculateKmeans();
     
    
  }
    
    
}
