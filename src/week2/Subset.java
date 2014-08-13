package week2;

/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 2
	Programming Assignment #2
*/

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Subset {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		 while (!StdIn.isEmpty()) {
			 String item = StdIn.readString();
			 rq.enqueue(item);
		 }
		 
		 for (int i = 0; i < k; i++) {
			 StdOut.println(rq.dequeue());
		 }
	}
}
