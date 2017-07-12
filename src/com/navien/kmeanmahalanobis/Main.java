/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;

import java.io.IOException;
import java.util.List;

import com.navien.deprecated.KMeanChart;
import com.navien.deprecated.KMeansResult;

/**
 *
 * @author navien
 */
public class Main {

	public static void main(String[] args) throws IOException {

		String filename = "dataset3ID.csv";

		List<Triplet> tripletList = Triplet.readTripletsFromCSV(filename, true);

		int tripletSize = tripletList.size();
		String[] ids = new String[tripletSize];
		double[][] data = new double[tripletSize][2];

		for (int i = 0; i < tripletSize; i++) {
			Triplet t = tripletList.get(i);

			ids[i] = t.id;
			data[i] = new double[] { t.d1, t.d2 };
		}

		int clusterNumber = 2;

		int selectedType = 1;
		int p = 2;
		int Dim = 2;

		float threshold = 0.001f;
		//
		// Kmean1 kMean = new Kmean1(rawData, threshold, clusterNumber, selectedType, p, Dim);
		Kmeans kMean = new Kmeans(data, threshold, clusterNumber, selectedType, p, Dim, ids);

		kMean.plotDataset(data);

		KMeanChart chart = new KMeanChart("KMean Algorithm", kMean);

		chart.plotKMeansResult();
		chart.pack();
		chart.setVisible(true);

		KMeansResult result = kMean.calculateKmeans();

	}

}
