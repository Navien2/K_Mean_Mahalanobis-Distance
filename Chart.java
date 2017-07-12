/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.kmeanmahalanobis;  // put com  in package creation to make it unique not clash with other code and packagees

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

import javax.swing.*;
import java.awt.*;


/**
 * A scatter plot demo using the {@link XYDotRenderer} class.
 */
public class Chart extends ApplicationFrame {
    public XYPlot plot;
    private int plotCount;

    public void addPlot(double[] x, double[] y) {
        XYDataset dataset = new SampleXYDataset2(x, y);
        plot.setDataset(plotCount, dataset);
        plot.setRenderer(plotCount, new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES));
        plotCount++;
    }

    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title the frame title.
     */
    public Chart(String title, double[] x, double[] y) {
        super(title);
        JPanel chartPanel = createDemoPanel(x, y);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createDemoPanel(double[] x, double[] y) {
        XYDataset dataset = new SampleXYDataset2(x, y);
        JFreeChart chart = ChartFactory.createScatterPlot("Data Plot",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        plotCount = 1;
        plot = (XYPlot) chart.getPlot();

        //plot.setDomainTickBandPaint(new Color(200, 200, 100, 100));
        plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotWidth(5);
        renderer.setDotHeight(5);
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

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    /*public static void main(String[] args) {
        Chart demo = new Chart(
                "JFreeChart: ScatterPlotDemo4.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }*/

}
