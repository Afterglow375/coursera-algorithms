package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size = 0;
	public int capacity = 1;
	private Item[] items = (Item[]) new Object[capacity];
	
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
		
		// If the array is full, double its length
		if (size == capacity) {
			resizeArray(capacity*2);
		}
	
		items[size] = item;
		size++;
	}
	
	// Delete and return a random item
	public Item dequeue() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty queue");
		
		// Swap a random element with the last element in the array
		int index = randomIndex();
		Item item = items[index];
		items[index] = items[size-1];
		items[size-1] = null;		
		size--;
		
		// If the number of items is 1/4 the capacity, halve the array
		if (size <= capacity/4) {
			resizeArray(capacity/2);
		}
		
		return item;
	}
	
	// Return but do not delete a random item
	public Item sample() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty queue");
		
		return items[randomIndex()];
	}
	
	private void resizeArray(int newCapacity) {
		Item[] arr = (Item[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			arr[i] = items[i];
		}
		items = arr;
		capacity = newCapacity;
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {
		Item[] newArray = (Item[]) new Object[size];
		int index = 0;
		
		// Creates a randomly shuffled array in O(2N)
		public QueueIterator() {
			for (int i = 0; i < size; i++) {
				newArray[i] = items[i]; 
			}
			StdRandom.shuffle(newArray);
		}
		
		@Override
		public boolean hasNext() {
			return index < newArray.length;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported");
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException("next() called without any more items");
			
			Item item = newArray[index];
			newArray[index] = null; // Preventing loitering
			index++;
			return item;
		}
	}	
	 
	private int randomIndex() {
		return StdRandom.uniform(0, size);
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(5);
		rq.enqueue(10);
		rq.enqueue(15);
		for (int i : rq) {
			System.out.println(i);
		}
		System.out.println("Sample: " + rq.sample());
		System.out.println("Dequeue: " + rq.dequeue());
		for (int i : rq) {
			System.out.println(i);
		}
	}
}
