/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navien.deprecated;

/**
 * myResult1 = new KMeansResult(5, 6); myResult2 = new KMeansResult(6, 7);
 *
 * myResult1 == myResult2 ?
 *
 * @author nwayyin
 */
public class KMeansResult {

    private int[] labels;

    private double[][] centroids;

    /**
     *
     * @param labels
     * @param centroids
     */
    public KMeansResult(int[] labels, double[][] centroids) {
        this.labels = labels;
        this.centroids = centroids;
    }

    /**
     *
     */
    public KMeansResult() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public KMeansResult(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public int[] getLabels() {
        return labels;
    }

    /**
     *
     * @param labels
     */
    public void setLabels(int[] labels) {
        this.labels = labels;
    }

    /**
     *
     * @return
     */
    public double[][] getCentroids() {
        return centroids;
    }

    /**
     *
     * @param centroids
     */
    public void setCentroids(double[][] centroids) {
        this.centroids = centroids;
    }

}
