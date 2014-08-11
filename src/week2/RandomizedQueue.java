package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size = 0;
	
	public RandomizedQueue() {

	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException("Inserted item cannot be null");
	}
	
	// Delete and return a random item
	public Item dequeue() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty queue");
	}
	
	// Return but do not delete a random item
	public Item sample() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty queue");
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}

	public static void main(String[] args) {
		
	}
}
