import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] Queue;
	private int size;
	
	public RandomizedQueue() {
		int defSize = 1;
		this.Queue = (Item []) new Object[defSize];
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public int size() {
		return size;		
	}
	
	public void enqueue(Item item)
	{	
		if (item == null) {
			throw new NullPointerException("Cannot add null items");			
		}
		
		if(this.size == Queue.length) {
			Item [] resize = (Item []) new Object[Queue.length * 2];
		
			for(int i = 0; i < Queue.length; i++)
				resize[i] = Queue[i];
			
			this.Queue = resize;
		}
		Queue[size] = item;
		
		this.size++;
	}
	
	private int getIndex() {
		while(true) {
			int rand = StdRandom.uniform(this.size);
			if(Queue[rand] != null)
				return rand;
		}
	}
	
	public Item dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		
		int rand = getIndex();
		Item deq = Queue[rand];
		Queue[this.size] = null;
		
		this.size--;
		
		if(this.Queue.length > 4 && this.size <= Queue.length/4) {
			Item [] resize = (Item []) new Object[Queue.length/2];
			
			for(int i = 0; i <= this.size; i++)
				resize[i] = Queue[i];
		
			this.Queue = resize;
		}
		
		return deq;
	}
	
	public Item sample() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
				
		return Queue[getIndex()];
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator(Queue, size);
	}
	

}
