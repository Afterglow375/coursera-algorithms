/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 1
*/

package week1;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	public double[] fractions; // Fractions of open sites
	public int T;
	private double mean;
	private double stddev;

	// T = number of percolations to simulate, N = size of the percolation grid (N x N)
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException("Invalid N or T");
		
		this.fractions = new double[T];
		this.T = T;
		
		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			int openSites = 0;
			int row, column;
			
			while (! (p.percolates())) {
				do { // Randomly pick a non-open site
					row = StdRandom.uniform(1, N+1);
					column = StdRandom.uniform(1, N+1);
				} while (p.isOpen(row, column));
				
				p.open(row, column);
				openSites++;
			}
			
			fractions[i] = (double) openSites / (N*N);
		}
		
		this.mean = StdStats.mean(fractions);
		this.stddev = StdStats.stddev(fractions);
	}
	
	public double mean() {
		return mean;
	}
	
	public double stddev() {
		return stddev;
	}
	
	public double confidenceLo() {
		return mean - (1.96*stddev)/Math.sqrt(T);
	}
	
	public double confidenceHi() {
		return mean + (1.96*stddev)/Math.sqrt(T);
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(N, T);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}
}
