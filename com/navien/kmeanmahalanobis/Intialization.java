/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.util.Random;

/**
 * Do not repeat yourself
 * @author nawin
 */
public class Intialization {
    
    static void printMatrix(double[][] mat) {
        int r = mat.length;
            int c = mat[0].length;
        for (int i = 0; i<r; i++) {
                for(int j = 0; j<c; j++) {
                    System.out.print(mat[i][j]);
                    System.out.print(", ");
                }
                System.out.println();
            }
    }
    
    static public double[][] computeCovarianceMatrix(double[][] data) {
        //calculate mean:
        int data_rows = data.length;
        int data_cols = data[0].length;
        double[] mean = new double[data_cols];

        for (int c = 0; c < data_cols; c++) {
                mean[c] = 0;
                for (int r = 0; r < data_rows; r++) { 
                        mean[c] += data[r][c]; 
                }
                mean[c] /= data_rows;
        }

	//compute covariance	
        final double[][] mat = new double[data_cols][data_cols];
        for (int r = 0; r < data_cols; r++) { // rows
          for (int c = r; c < data_cols; c++) { // cols

            double sum = 0;
            for (int i = 0; i < data_rows; i++) {
              sum += (data[i][r]-mean[c]) * (data[i][c]-mean[c]);
            }
            mat[r][c] = mat[c][r] = sum/(data_rows - 1);
          }
        }
        return mat;
	  }
    
   //mean randomly 
    
  static   public double[][] datasample(double[][] data, int k) {
        int     n_indeces = k;
        int     data_rows = data.length;
        int     data_cols = data[0].length;
        
        double[][] y = new double[n_indeces][data_cols];
        Random rand = new Random(System.currentTimeMillis()); // would make this static to the class    
        int[] data_indeces = new int[n_indeces];
        for (int i = 0; i < n_indeces; i++) {
             // be sure to use Vector.remove() or you may get the same item twice
             data_indeces[i] = rand.nextInt(data_rows);
             for (int j = 0; j<data_cols; j++) {
                 y[i][j] = data[data_indeces[i]][j];
             }
             System.out.println(data_indeces[i]+2);
        }
        
        return y;
    }
    
    
}
