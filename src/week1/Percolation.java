package week1;
/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 1
	Programming Assignment 1
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N;
	private boolean[][] grid;
	private WeightedQuickUnionUF uf;
	private int endSite;
	
	public Percolation(int N) {
		this.N = N;
		this.grid = new boolean[N][N];
		this.endSite = N*N+1;
		this.uf = new WeightedQuickUnionUF(endSite+1);
		
		// Connect it to our top virtual site if it's in the first row 
		for (int i = 1; i <= N; i++) {
			uf.union(0, i);
			uf.union(endSite, endSite-i);
		}
	}
	
	public void open(int i, int j) {
		validateIndexes(i, j);
		
		grid[i-1][j-1] = true;
		int ufIndex = coordinateToIndex(i, j);
		
		// Union adjacent open sites...
		if (i != 1) { // Above
			unionSites(ufIndex, i-1, j);
		}
		if (i != N) { // Down
			unionSites(ufIndex, i+1, j);
		}
		if (j != 1) { // Left
			unionSites(ufIndex, i, j-1);
		}
		if (j != N) { // Right
			unionSites(ufIndex, i, j+1);
		}
	}
	
	// Unions the two sites assuming the first is open and tests if the 2nd is open
	private void unionSites(int ufIndex, int i, int j) {
		if (isOpen(i, j)) {
			// If the site is on the bottom row, connect it to the bottom virtual site
			uf.union(ufIndex, coordinateToIndex(i, j));
		}
	}
	
	public boolean isOpen(int i, int j) {
		validateIndexes(i, j);
		
		return grid[i-1][j-1];
	}
	
	public boolean isFull(int i, int j) {
		validateIndexes(i, j);
		
		if (isOpen(i, j)) {
			return uf.connected(0, coordinateToIndex(i, j));
		}
		return false;
	}
	
	public boolean percolates() {
		return uf.connected(0, endSite);
	}
	
	// Throws an error when given invalid xy coordinates
	private void validateIndexes(int i, int j) {
		if ((i <= 0 || i > N) || (j <= 0 || j > N))
			throw new IndexOutOfBoundsException("Coordinate is out of bounds");
	}
	
	// Converts an xy coordinate into a single int index for the union-find data structure
	private int coordinateToIndex(int i, int j) {
		return (i-1)*N+j;
	}
	
}
