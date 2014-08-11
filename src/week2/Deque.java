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
	private Node first, last;
	
	public Deque() {
		this.size = 0;
	}
	
	private class Node {
		Item item;
		Node next;
		Node prev;
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
		
		Node oldFirst = first;
		first = new Node();
		
		first.item = item;
		first.next = oldFirst;
		first.prev = null;
		
		if (size == 0) {
			last = first;
		}
		else {
			oldFirst.prev = first;
		}
		
		size++;
	}
	
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException("Inserted item cannot be null");
		
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		last.next = null;
		
		if (size == 0) {
			first = last;
		}
		else {
			oldLast.next = last;
		}
		
		size++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty deque");
		
		Item item = first.item;
		first = first.next;
		first.prev = null;
		size--;
		return item;
	}
	
	public Item removeLast() {
		if (isEmpty()) 
			throw new NoSuchElementException("Cannot remove from an empty deque");
		
		Item item = last.item;
		last = last.prev;
		last.next = null;
		size--;
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		Node nodePointer = first;
		
		@Override
		public boolean hasNext() {
			return nodePointer != null;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported");
		}

		@Override
		public Item next() {
			if (!hasNext()) 
				throw new NoSuchElementException("next() called without any more items");
			Item item = nodePointer.item;
			nodePointer = nodePointer.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(5);
		deque.addFirst(10);
		deque.addLast(20);
		deque.addFirst(15);
		deque.addLast(25);
		for (int i : deque) {
			System.out.println(i);
		}
	}
}
