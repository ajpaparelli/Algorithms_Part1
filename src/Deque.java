import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private class Node {
		Item contents;
		Node Next;
		Node Prev;
		
	}
	
	
	private Node head;
	private Node tail;
	private int qsize; 
	

	
	public Deque() {
		head = null;
		tail = null;					
	}
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public int size() {
		return qsize;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add null items");			
		}
		
		Node newNode = new Node();
		newNode.contents = item;
		
		this.qsize++;
		
		if (qsize == 1) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			Node tmpNode = this.head;
			this.head = newNode;
			newNode.Next = tmpNode;			
			tmpNode.Prev = newNode;
		}			
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add null items");			
		}
		
		Node newNode = new Node();
		newNode.contents = item;
		
		this.qsize++;
		
		if (qsize == 1) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			this.tail.Next = newNode;
			newNode.Prev = this.tail;
			this.tail = this.tail.Next;
		}
	}
	
	public Item removeFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		
		Item tmpItem = this.head.contents;
		
		this.qsize--;
		
		this.head = this.head.Next;
		
		if (size() == 0) {
			this.tail = null;
		}
		else {
			this.head.Prev = null;
		}
		return tmpItem;
	}
			
	public Item removeLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		
		Item tmpItem = this.tail.contents;
		
		this.qsize--;
		
		this.tail = this.tail.Prev;
		
		if (size() == 0) {
			this.head = null;
		}
		else {
			this.tail.Next = null;
		}
		return tmpItem;
	}		
	
	public Iterator <Item> iterator() {
		return new QueueIterator();
	}
	
	public class QueueIterator implements Iterator<Item> {
		private Node cur = head;
		
		@Override
		public boolean hasNext() {
			return cur != null;			
		}
		
		@Override
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException("No more objects in to iterate through");				
			}
			Item i = cur.contents;
			cur = cur.Next;
			return i;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Iteratore remove function not supported");
		}
	}
}
