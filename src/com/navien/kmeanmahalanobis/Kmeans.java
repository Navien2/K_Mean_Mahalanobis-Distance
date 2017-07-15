/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import com.navien.deprecated.KMeansResult;

/**
 *
 * @author naween
 */
public class Kmeans {

	private String[] Id;
	private int Dim;
	private double[][] data;
	private double[][] centroids;
	private double minChange;
	private int clusterNumber;
	private int type;
	private double[][] sigma;

	int i;

	/**
	 *
	 * @param data
	 * @param minChange
	 * @param clusterNumber
	 * @param type
	 * @param sigma
	 * @param p
	 */
	public Kmeans(double[][] data, double minChange, int clusterNumber, int type, int p, int Dim,
			String[] Id) {
		this.data = data;
		this.minChange = minChange;
		this.clusterNumber = clusterNumber;
		this.type = type;
		this.centroids = Utils.getRandomPoints(data, clusterNumber);
		this.sigma = Utils.getCovarianceMatrix(data);
		this.Dim = Dim;
		this.Id = Id;

	}

	public KMeansResult calculateKmeans() {

		int[] labels = null; // IDX assigned to clusters

		// double[][] Sigma = Intialization.computeCovarianceMatrix(data); // for mahal distance
		boolean changed = true; // ?

		while (changed) {
			labels = assignLabels(data, centroids, type);

			double[][] newCENTROIDS = new double[centroids.length][Dim];

			// Compute new centeroid positions ( update centeriod
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
				newCENTROIDS[i][0] = mean[0] / count;
				newCENTROIDS[i][1] = mean[1] / count;
			}

			changed = false;

			// Check if any of the centeriod changes exceed the threshold
			for (int i = 0; i < centroids.length; i++) {
				if (getDistances(centroids[i], newCENTROIDS[i]) >= minChange) { // how the distance
																				// change here ???
					changed = true;
					break;
				}
			}

			centroids = newCENTROIDS;

		}
		KMeansResult result = new KMeansResult(labels, centroids); // produce error when we remove
																	// static from class kmean
																	// result

		return result;
	}

	public int[] assignLabels(double[][] data, double[][] CENTROIDS, int type) {
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

	/**
	 * Returns correct distance calculation for given type
	 * 
	 * @param subData
	 *            subset of data
	 * @param subCentroid
	 *            subset of centroid
	 * @return
	 */
	public double getDistances(double[] subData, double[] subCentroid) {
		double distances = 0;
		switch (type) {
		case 0:
			distances = Utils.euclideanDistance(subData, subCentroid);
			break;
		case 1:
			distances = Utils.mahalanobisDistance(subData, subCentroid, sigma);
			break;

		}
		return distances;
	}

}
