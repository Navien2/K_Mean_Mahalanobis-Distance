/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Do not repeat yourself
 * 
 * @author nawin
 */
public class Utils {

	/**
	 * Prints a 2-dim array of doubles
	 * 
	 * @param matrix
	 */
	static void printMatrix(double[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]);

				if (j < matrix[0].length - 1)
					System.out.print(", ");
			}
			System.out.println();
		}

	}

	static public double[][] computeCovarianceMatrix(double[][] data) {
		// calculate mean:
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

		// compute covariance
		final double[][] mat = new double[data_cols][data_cols];
		for (int r = 0; r < data_cols; r++) { // rows
			for (int c = r; c < data_cols; c++) { // cols

				double sum = 0;
				for (int i = 0; i < data_rows; i++) {
					sum += (data[i][r] - mean[c]) * (data[i][c] - mean[c]);
				}
				mat[r][c] = mat[c][r] = sum / (data_rows - 1);
			}
		}
		return mat;
	}

	/**
	 * return nPoints randomly chosen points from the given data
	 * 
	 * @param data
	 * @param nPoints
	 * @return a 2-dim double array
	 */
	static public double[][] getRandomPoints(double[][] data, int nPoints) {
		int nRows = data.length;
		int nCols = data[0].length;

		if (nPoints > nRows)
			throw new RuntimeException(
					"Requested more random points than total number of points in data");

		double[][] randomPoints = new double[nPoints][nCols];

		List<Integer> randomIndices = new ArrayList<Integer>();
		Random rand = new Random();

		for (int i = 0; i < nPoints; i++) {
			// keep generating a random number until
			int randomIndex = rand.nextInt(nRows);
			while (randomIndices.contains(randomIndex))
				randomIndex = rand.nextInt(nRows);

			randomIndices.add(randomIndex);
			randomPoints[i] = data[randomIndex];
		}

		return randomPoints;
	}

	public static void main(String[] args) {
		double[][] data = { { 1, 1 }, { 2, 2 }, { 3, 3 } };

		double[][] randPoints = getRandomPoints(data, 2);
		System.out.println(Arrays.deepToString(randPoints));
	}
}
