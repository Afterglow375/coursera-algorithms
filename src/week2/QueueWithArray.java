package week2;

/*	
	Written by Alex Tatusko
	Coursera Algorithms Part 1 Week 2
	Queue implementation using an array
*/

public class QueueWithArray {
	private String[] data;
	private int head, tail, capacity;
	
	public QueueWithArray() {
		this.data = new String[1]; // Initial capacity is 1
		this.head = 0;
		this.tail = 0;
		this.capacity = 1;
	}
	
	public boolean isEmpty() {
		return data[head] == null;
	}
	
	public void enqueue(String item) {
		// If max capacity, double the length of the array
		if (data[tail] != null) {
			resize(capacity*2);
		}
		
		data[tail] = item;
		tail = (tail+1) % capacity;
	}
	
	public String dequeue() {
		String item = data[head];
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
		String[] newData = new String[capacity];
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
		QueueWithArray q = new QueueWithArray();
		q.enqueue("Cat");
		q.enqueue("Dog");
		q.enqueue("Lamb");
		q.enqueue("Goat");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.printInfo();
	}
}
