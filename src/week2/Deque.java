package week2;
/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 2
	Programming Assignment #2
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size;	
	
	public Deque() {
		this.size = 0;
	}
	
	private class Node {
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException("Inserted item cannot be null");
	}
	
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException("Inserted item cannot be null");
			
	}
	
	public Item removeFirst() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty deque");
	}
	
	public Item removeLast() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty deque");
	}
	
	@Override
	public Iterator<Item> iterator() {
		return null;
	}

	public static void main(String[] args) {

	}

}
