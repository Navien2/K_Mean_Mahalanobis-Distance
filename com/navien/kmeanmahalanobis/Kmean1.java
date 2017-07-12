/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.navien.kmeanmahalanobis.Intialization;
import com.navien.kmeanmahalanobis.InvMat;
import com.navien.kmeanmahalanobis.ReadCSV;
import com.navien.kmeanmahalanobis.KMeanChart;
import com.navien.kmeanmahalanobis.matrix;
import com.navien.kmeanmahalanobis.vector;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author naween
 */
public class Kmean1 {
   
    private String [] Id;
   
    /**
     * 
     */
    
    private int Dim;

    /**
     *
     */
    private double[][] data;

    /**
     *
     */
    private double[][] centroids;

    /**
     *
     */
    private double minChange;

    /**
     *
     */
    private int clusterNumber;

    /**
     * Field or global variable
     */
    private int type;
    /**
     *
     */

    private double[][] sigma;

    
    int  i;

    /**
     *
     * @param data
     * @param minChange
     * @param clusterNumber
     * @param type
     * @param sigma
     * @param p
     */
    public Kmean1(double[][] data, double minChange, int clusterNumber, int type,int p , int Dim , String []Id  ) {
        this.data = data;
        this.minChange = minChange;
        this.clusterNumber = clusterNumber;
        this.type = type;
        this.centroids = Intialization.datasample(data, clusterNumber);
        this.sigma = Intialization.computeCovarianceMatrix(data);
        this.Dim= Dim;
        this.Id=Id;
        
    }

  /**
   * 
   */
    public Kmean1(){
        
    }
    
    
    
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    // method1
    private static void plotResut(KMeansResult result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param dataSet 
     */
     public  void plotDatasSet(List<Data> dataSet ){   // change it to void then issue is gone
        
         double[] x = new double[dataSet.size()];
         double[] y = new double[dataSet.size()];
         
         for(int i = 0; i < dataSet.size(); i++){
             x[i] = dataSet.get(i).D1;
             y[i] = dataSet.get(i).D2;
         }
         
         Chart kChart = new Chart("Data", x, y);
         
         kChart.pack();

            RefineryUtilities.centerFrameOnScreen(kChart);

            kChart.setVisible(true);

     }
  
     
     
 /**
  * 
  * @param dataSet 
  */
        
 public  void plotDataset(double [][] dataSet ){   // change it to void then issue is gone
                
          double[][] dx = matrix.Transpose(dataSet);

            Chart kChart = new Chart("Data", dx[0], dx[1]);

            kChart.pack();

            RefineryUtilities.centerFrameOnScreen(kChart);

            kChart.setVisible(true);

        } 
 //////////////////////////////////////////////////////////////////////////////
    /**
     * KMeansResult is class 
     * @return
     */
    //methode2
    public KMeansResult calculateKmeans() {

        int[] labels = null;  //  IDX assigned to clusters 

        // double[][] Sigma = Intialization.computeCovarianceMatrix(data);  // for mahal distance 
        boolean changed = true;  // ?

        while (changed) {
            labels = assignLabels(data, centroids, type);

            double[][] newCENTROIDS = new double[centroids.length][Dim];

            // Compute new centeroid  positions ( update centeriod 
            for (int i = 0; i < centroids.length; i++) {
                
                double[] mean = new double[Dim];

                int count = 0;
                
                for (int j = 0; j < data.length; j++) {
                    
                    if (labels[j] == i) {
                        mean[0] += data[j][0];
                        mean[1] += data[j][1];
                        count++;
                    }
                }
                newCENTROIDS[i][0] = mean[0] / (double) count;
                newCENTROIDS[i][1] = mean[1] / (double) count;
            }

            changed = false;

            // Check if any of the centeriod changes exceed the threshold
            for (int i = 0; i < centroids.length; i++) {
                if (getDistances(centroids[i], newCENTROIDS[i]) >= minChange) {                 // how the distance change here  ??? 
                    changed = true;
                    break;
                }
            }

            centroids = newCENTROIDS;

        }
        KMeansResult result = new KMeansResult(labels, centroids);   // produce error when we remove static  from class kmean result
       
        return result;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * if(jRadio1 == true){ type = 0} else if ... )
     *
     * @param data
     * @param CENTROIDS
     * @param Sigma
     * @param type
     * @return
     * @throws Exception we add int type with case to make it work with
     * Radiobutton
     */
    public int[] assignLabels(double[][] data, double[][] CENTROIDS, int type ) {
        // Initialize labels
        int[] labels = new int[data.length];

        // Find the cluster closest to this data and
        // assign it as the new label
        for (int i = 0; i < data.length; i++) {
            double minDistance = Double.MAX_VALUE;
            
            for (int j = 0; j < CENTROIDS.length; j++) {
                double distances = getDistances(data[i], CENTROIDS[j]);

                if (distances < minDistance) {
                    minDistance = distances;
                    labels[i] = j;
                }
            }
        }

        return labels;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double distance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *Malanobis
     * @param a
     * @param b
     * @param Sigma
     * @return
     * @throws Exception
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    public static double distance1(double[] a, double[] b, double[][] Sigma) {

        double[][] Sigma_inverse = InvMat.Inverse(Sigma);

        double[][] diff = new double[1][a.length];

        for (int i = 0; i < a.length - 1; i++) {
            diff[0][i] = a[i] - b[i];
        }

        double result[][] = vector.mulMatrix(diff, Sigma_inverse);

        result = vector.mulMatrix(result, matrix.Transpose(diff));
        return Math.sqrt(result[0][0]);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns correct distance calculation for given type
     * @param subData subset of data
     * @param subCentroid subset of centroid
     * @return 
     */
    public double getDistances(double[] subData, double[] subCentroid) {
        double distances = 0;
        switch (type) {
            case 0:
                distances = distance(subData, subCentroid);
                break;
            case 1:
                distances = distance1(subData, subCentroid, sigma);
                break;
            
        }
        return distances;
    }

 
   /**
   * 
   * @return 
   */  
    
    
    
 //public static  double dunnesIndex()   {
     
 //}
    
    
  /**
   * 
   * @return 
   */  
    
  // public static double davisBoulden(){
        
        
    //}
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // in generation of getter nd setter we do not have type and p ??
    
    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double[][] getCentroids() {
        return centroids;
    }

    public void setCentroids(double[][] centroids) {
        this.centroids = centroids;
    }

    public double getMinChange() {
        return minChange;
    }

    public void setMinChange(double minChange) {
        this.minChange = minChange;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public String[] getId() {
        return Id;
    }

    public void setId(String[] Id) {
        this.Id = Id;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }
    
    

}
