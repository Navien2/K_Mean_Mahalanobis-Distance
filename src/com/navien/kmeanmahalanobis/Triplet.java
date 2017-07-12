package com.navien.kmeanmahalanobis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
/**
 * This class holds 3 itmes
 * 
 * @author nwayyin
 */
public class Triplet {

	String id;
	double d1;
	double d2;

	public Triplet(String id, double d1, double d2) {
		this.id = id;
		this.d1 = d1;
		this.d2 = d2;
	}

	/**
	 * 
	 * @param line
	 *            a string containing three comma separated items e.g.
	 *            10338017,-2.704037912,1.275525478
	 */
	public Triplet(String line) {
		String[] parts = line.split(",");

		if (parts.length != 3)
			throw new RuntimeException("Invalid format of line: " + line);

		this.id = parts[0];
		this.d1 = Double.parseDouble(parts[1]);
		this.d2 = Double.parseDouble(parts[2]);
	}

	@Override
	public String toString() {
		return id + "," + d1 + "," + d2;
	}

	public static String getHeader() {
		return "ID,D1,D2";
	}

	/**
	 * Reads a file and returns a list of Triplets
	 * 
	 * @param filename
	 *            the name to read data from
	 * @param hasTitle
	 *            if true skip the first line of the file since it is a title
	 * 
	 * @return a list of Triplets
	 */
	public static List<Triplet> readTripletsFromCSV(String filename, boolean hasTitle) {

		List<Triplet> result = new ArrayList<Triplet>();

		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (in.hasNext()) {
			String line = in.next();

			if (hasTitle) {
				hasTitle = false;
				continue;
			}

			result.add(new Triplet(line));
		}

		in.close();

		return result;
	}
}
