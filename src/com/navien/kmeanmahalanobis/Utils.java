/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.navien.deprecated.InvMat;
import com.navien.deprecated.matrix;
import com.navien.deprecated.vector;

/**
 * Do not repeat yourself
 * 
 * @author nawin
 */
public class Utils {

	private static Random RNG = new Random();

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

	/**
	 * The mean of a 2-dim array
	 * 
	 * @param data
	 * @return
	 */
	public static double[] getMean(double[][] data) {
		int noRows = data.length;
		int noCols = data[0].length;

		double[] mean = new double[noCols];
		for (int c = 0; c < noCols; c++) {
			mean[c] = 0;
			for (int r = 0; r < noRows; r++)
				mean[c] += data[r][c];

			mean[c] /= noRows;
		}

		return mean;
	}

	/**
	 * Return the covariance value of two values from a given mean
	 */
	public static double getCovariance(double x, double y, double mean) {
		return (x - mean) * (y - mean);
	}

	/**
	 * Return the covariance matrix of data. Each column of the given data represents a random
	 * variable
	 */
	static public double[][] getCovarianceMatrix(double[][] data) {

		double[] mean = getMean(data);

		int noRows = data.length;
		int noCols = data[0].length;

		final double[][] covarianceMatrix = new double[noCols][noCols];

		for (int r = 0; r < noCols; r++) {
			for (int c = r; c < noCols; c++) {

				double sum = 0;
				for (int i = 0; i < noRows; i++)
					sum += getCovariance(data[i][r], data[i][c], mean[c]);

				covarianceMatrix[r][c] = covarianceMatrix[c][r] = sum / (noRows - 1);
			}
		}

		return covarianceMatrix;
	}

	/**
	 * return nPoints randomly chosen points from the given data
	 * 
	 * @param data
	 *            all points
	 * @param nPoints
	 *            the number of points to return
	 * 
	 * @return a 2-dim double array
	 */
	static public double[][] getRandomPoints(double[][] data, int nPoints) {
		int nRows = data.length;
		int nCols = data[0].length;

		if (nPoints > nRows)
			throw new RuntimeException(
					"Requested more random points than total number of points in data");

		double[][] randomPoints = new double[nPoints][nCols];

		// keep track of indices already selected so they are not selected again
		List<Integer> randomIndices = new ArrayList<Integer>();

		for (int i = 0; i < nPoints; i++) {

			// keep generating a random number until a new one is found
			int randomIndex = RNG.nextInt(nRows);
			while (randomIndices.contains(randomIndex))
				randomIndex = RNG.nextInt(nRows);

			// save the index selected
			randomIndices.add(randomIndex);

			// add the random point to the results array
			randomPoints[i] = data[randomIndex];
		}

		return randomPoints;
	}

	/**
	 * Calculates Euclidean distance of two points
	 * 
	 * @param a
	 *            first point
	 * @param b
	 *            second point
	 * @return the euclidean distance as a double value
	 */
	public static double euclideanDistance(double[] a, double[] b) {
		return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
	}

	/**
	 * Malanobis
	 * 
	 * @param a
	 * @param b
	 * @param Sigma
	 * @return
	 * @throws Exception
	 */
	public static double mahalanobisDistance(double[] a, double[] b, double[][] Sigma) {

		double[][] Sigma_inverse = InvMat.Inverse(Sigma);

		double[][] diff = new double[1][a.length];

		for (int i = 0; i < a.length - 1; i++) {
			diff[0][i] = a[i] - b[i];
		}

		double result[][] = vector.mulMatrix(diff, Sigma_inverse);

		result = vector.mulMatrix(result, matrix.Transpose(diff));
		return Math.sqrt(result[0][0]);
	}

	private static void testGetRandomPoints() {
		double[][] data = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		double[][] randPoints = getRandomPoints(data, 2);
		System.out.println(Arrays.deepToString(randPoints));
	}

	private static void testEuclideanDistance() {
		double[] a = { 0, 0 };
		double[] b = { 3, 4 };

		double distance = euclideanDistance(a, b);
		System.out.println(distance);
	}

	private static void testGetCovarianceMatrix() {
		double[][] data = { { 2, 1 }, { 4, 2 }, { 6, 3 } };
		double[][] covMatrix = getCovarianceMatrix(data);
		System.out.println(Arrays.deepToString(covMatrix));
	}

	public static void main(String[] args) {
		// testGetRandomPoints();
		// testEuclideanDistance();
		testGetCovarianceMatrix();
	}
}
