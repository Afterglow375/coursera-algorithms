package week2;
/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 2
	Queue implementation using an array
*/

import java.util.Iterator;

public class QueueWithArray<Item> implements Iterable<Item> {
	private Item[] data;
	private int head, tail, capacity;
	
	public QueueWithArray() {
		this.data = (Item[]) new Object[1]; // Initial capacity is 1
		this.head = 0;
		this.tail = 0;
		this.capacity = 1;
	}
	
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item> {
		private int loopCounter = 0;
		private int i = ((tail-1) < 0) ? capacity-1 : tail-1;
		
		public boolean hasNext() {
			return data[i] != null && loopCounter != capacity;
		}
		
		public Item next() {
			Item item = data[i];
			i = ((i-1) < 0) ? capacity-1 : i-1;
			return item;
		}
	}
	
	public boolean isEmpty() {
		return data[head] == null;
	}
	
	public void enqueue(Item item) {
		// If max capacity, double the length of the array
		if (data[tail] != null) {
			resize(capacity*2);
		}
		
		data[tail] = item;
		tail = (tail+1) % capacity;
	}
	
	public Item dequeue() {
		Item item = data[head];
		data[head] = null;
		head = (head+1) % capacity;
		
		// If 1/4th the capacity, halve the length of the array
		int numElems = tail - head;
		if (numElems < 0) {
			numElems = (capacity + numElems);
		}
		if (numElems <= capacity/4) {
			resize(capacity/2);
		}
		
		return item;
	}
	
	private void resize(int capacity) {
		Item[] newData = (Item[]) new Object[capacity];
		int i = head;
		do {
			newData[i-head] = data[i];
			i = (i+1) % this.capacity;
		} while (data[i] != null && i != head);

		this.tail = capacity / 2;
		this.head = 0;
		this.capacity = capacity;
		this.data = newData;
	}

	private void printInfo() {
		System.out.println("==============");
		System.out.println("Head: " + head);
		System.out.println("Tail: " + tail);
		System.out.println("Capacity: " + capacity);
		String items = "";
		for (int i = 0; i < capacity; i++) {
			if (data[i] == null) {
				items += "null ";
			}
			else { 
				items += data[i] + " ";
			}
		}
		System.out.println(items);
	}
	
	public static void main(String[] args) {
		QueueWithArray<Integer> q = new QueueWithArray<Integer>();
		q.enqueue(10);
		q.enqueue(15);
		q.enqueue(5);
		q.enqueue(-5);
		q.printInfo();
		System.out.println("==============");
		for (int item : q) {
			System.out.println(item);
		}
		System.out.println("==============");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.printInfo();
	}
}
