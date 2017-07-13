/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.navien.deprecated;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import com.navien.kmeanmahalanobis.Kmeans;
import com.navien.kmeanmahalanobis.Triplet;

/**
 * Plots a Kmean instance
 */
public class KMeanChart extends ApplicationFrame {

	public Kmeans kmean;

	public XYPlot plot;

	private int plotCount;

	public KMeanChart(String title, Kmeans kmean) {
		super(title);
		this.kmean = kmean;
	}

	public KMeanChart(String title, double[] x, double[] y, double[] x1, double[] y1) {
		super(title);
		JPanel chartPanel = createDemoPanel(x, y, x1, y1);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	public KMeanChart(String title, double[] x, double[] y, double[] x1, double[] y1, double[] x2,
			double[] y2) {
		super(title);
		JPanel chartPanel = createDemoPanel(x, y, x1, y1, x2, y2);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	public void addPlot(double[] x, double[] y) {
		XYDataset dataset = new SampleXYDataset2(x, y);
		plot.setDataset(plotCount, dataset);
		plot.setRenderer(plotCount, new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES));
		plotCount++;
	}

	// normal kmean
	public void plotChart(double[][] xs, double[][] ys, int k, double[][] centroids) {
		JPanel chartPanel = createDemoPanel(xs, ys, k, centroids);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 670));
		setContentPane(chartPanel);
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 *
	 * @return A panel.
	 */
	public JPanel createDemoPanel(double[] x, double[] y, double[] x1, double[] y1) {
		XYDataset dataset = new SampleXYDataset2(x, y);
		JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot", "X", "Y", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		plotCount = 1;
		plot = (XYPlot) chart.getPlot();
		plot.setDataset(0, dataset);
		plot.setDataset(1, new SampleXYDataset2(x1, y1));
		// plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
		plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
		XYDotRenderer renderer0 = new XYDotRenderer();
		XYDotRenderer renderer1 = new XYDotRenderer();
		renderer0.setDotWidth(4);
		renderer0.setDotHeight(4);

		renderer1.setDotWidth(4);
		renderer1.setDotHeight(4);
		plot.setRenderer(0, renderer0);
		plot.setRenderer(1, renderer1);

		plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.red);
		plot.getRendererForDataset(plot.getDataset(1)).setSeriesPaint(0, Color.blue);

		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setAutoRangeIncludesZero(false);
		plot.getRangeAxis().setInverted(false);
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 *
	 * @return A panel.
	 */
	public JPanel createDemoPanel(double[] x, double[] y, double[] x1, double[] y1, double[] x2,
			double[] y2) {
		XYDataset dataset = new SampleXYDataset2(x, y);
		JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot", "X", "Y", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		plotCount = 1;
		plot = (XYPlot) chart.getPlot();
		plot.setDataset(0, dataset);
		plot.setDataset(1, new SampleXYDataset2(x1, y1));
		plot.setDataset(2, new SampleXYDataset2(x2, y2));
		// plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
		plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
		XYDotRenderer renderer0 = new XYDotRenderer();
		XYDotRenderer renderer1 = new XYDotRenderer();
		XYDotRenderer renderer2 = new XYDotRenderer();
		renderer0.setDotWidth(4);
		renderer0.setDotHeight(4);

		renderer1.setDotWidth(4);
		renderer1.setDotHeight(4);

		renderer2.setDotWidth(4);
		renderer2.setDotHeight(4);

		plot.setRenderer(0, renderer0);
		plot.setRenderer(1, renderer1);
		plot.setRenderer(2, renderer2);

		plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.red);
		plot.getRendererForDataset(plot.getDataset(1)).setSeriesPaint(0, Color.blue);
		plot.getRendererForDataset(plot.getDataset(2)).setSeriesPaint(0, Color.yellow);

		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setAutoRangeIncludesZero(false);
		plot.getRangeAxis().setInverted(false);
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	public JPanel createDemoPanel(double[][] xs, double[][] ys, int k, double[][] centroids) {
		XYDataset dataset = new SampleXYDataset2(xs[0], ys[0]);
		JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot", "X", "Y", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		plot = (XYPlot) chart.getPlot();

		// plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
		plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));

		plot.setDataset(0, dataset);

		for (int i = 1; i < xs.length; i++) {
			plot.setDataset(i, new SampleXYDataset2(xs[i], ys[i]));
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setDotWidth(4);
			renderer.setDotHeight(4);
			plot.setRenderer(i, renderer);
			plot.getRendererForDataset(plot.getDataset(i)).setSeriesPaint(0,
					Color.getHSBColor((float) i / (float) k, 1.0F, 1.0F));

		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		double[][] transpose = new double[centroids[0].length][centroids.length];

		for (int i = 0; i < transpose.length; i++) {
			for (int j = 0; j < transpose[0].length; j++) {
				transpose[i][j] = centroids[j][i];
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int i = 1 + xs.length; i < xs.length + transpose.length; i++) {
			plot.setDataset(i, new SampleXYDataset2(transpose[0], transpose[1]));
			XYDotRenderer renderer = new XYDotRenderer();
			renderer.setDotWidth(8);
			renderer.setDotHeight(8);
			plot.setRenderer(i, renderer);
			plot.getRendererForDataset(plot.getDataset(i)).setSeriesPaint(0, Color.WHITE);
			plot.getRendererForDataset(plot.getDataset(i));

		}

		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setAutoRangeIncludesZero(false);
		plot.getRangeAxis().setInverted(false);
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 *
	 * @return A panel.
	 */
	public JPanel createDemoPanel(double[] x, double[] y) {
		XYDataset dataset = new SampleXYDataset2(x, y);
		JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot", "X", "Y", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		plotCount = 1;
		plot = (XYPlot) chart.getPlot();

		// plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
		plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
		XYDotRenderer renderer = new XYDotRenderer();
		renderer.setDotWidth(4);
		renderer.setDotHeight(4);
		plot.setRenderer(renderer);
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setAutoRangeIncludesZero(false);
		plot.getRangeAxis().setInverted(false);
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	public void plotKMeansResult() throws IOException {

		KMeansResult result = kmean.calculateKmeans();

		int Dim = 2;
		double[][] xs = new double[kmean.getClusterNumber()][];
		double[][] ys = new double[kmean.getClusterNumber()][];

		for (int i = 0; i < kmean.getClusterNumber(); i++) {

			// i represent cluster 0, 1.
			System.out.print("Cluster[" + i + "] data: ");

			ArrayList dataList = new ArrayList();

			// Correct
			List<Triplet> clusterResult = new ArrayList<>();

			for (int j = 0; j < kmean.getData().length; j++) {
				// calling data belong to cluster i
				if (result.getLabels()[j] == i) {

					System.out.print("(" + kmean.getData()[j][0] + "," + kmean.getData()[j][1] + ","
							+ kmean.getId()[j] + "), "); // we should remove the last one since we
															// omit ID

					Triplet dataToAddToList = new Triplet(kmean.getId()[j], kmean.getData()[j][0],
							kmean.getData()[j][1]);

					clusterResult.add(dataToAddToList);

					// get(i) wil not work is empty so what iam getting :(
					// clusterResult.get(j).ID= kmean.getData()[j][0];
					// clusterResult.get(j).D2= kmean.getData()[j][1];
					dataList.add(new double[] { kmean.getData()[j][0], kmean.getData()[j][1] });

				}

			}

			// WriteCsvFileUsingObjects writeClusterData = new WriteCsvFileUsingObjects();

			// writeClusterData.writeDataToFile("ResultCluster.csv", clusterResult, true);

			System.out.println();

			Object[] dataObject = dataList.toArray();
			double[][] _data = new double[dataObject.length][Dim];
			for (int _i = 0; _i < _data.length; _i++) {
				for (int j = 0; j < _data[_i].length; j++) {
					_data[_i][j] = ((double[]) dataObject[_i])[j];
				}
			}

			double[][] m = matrix.Transpose(_data);
			xs[i] = m[0];
			ys[i] = m[1];
		}
		plotChart(xs, ys, kmean.getClusterNumber(), result.getCentroids());

	}

}
