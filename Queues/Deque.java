import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private int length;
	private Node<Item> first;
	private Node<Item> last;

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item>prev;
	}

	public Deque() {
		first = null;
		last = null;
		length = 0;
   	}                          // construct an empty deque

   	public boolean isEmpty() {
   		return length == 0;
   	}                // is the deque empty?

   	public int size() {
   		return length;
   	}                       // return the number of items on the deque

   	public void addFirst(Item item) {
   		if (item == null) 
   			throw new java.lang.NullPointerException("Adding a nul element");
   		else {
   			Node<Item> node = new Node<Item>();
   			node.item = item;
   			if (isEmpty()) {
	   			node.next = null;
	   			node.prev = null;
	   			first = node;
	   			last = node;
   			}
   			else {
   				node.next = first;
   				node.prev = null;
               first.prev = node;
   				first = node;
   			}
            length = length + 1;
   		}
  	}         // add the item to the front

   	public void addLast(Item item) {
   		if (item == null)
   			throw new java.lang.NullPointerException("Adding a null element");
   		else {
   			Node<Item> node = new Node<Item>();
  	 		node.item = item;
   			if (isEmpty()) {
   				node.next = null;
   				node.prev = null;
   				first = node;
   				last = node;
   			}
 	  		else {
   				node.next = null;
   				node.prev = last;
 	  			   last.next = node;
 	  			   last = node;
   			}
            length = length + 1;
   		}
   	}          // add the item to the end

   	public Item removeFirst() {
   		if (isEmpty())
   			throw new java.util.NoSuchElementException("Rempving from an empty list");
   		else {
   			Item rem = first.item;
   			first = first.next;
            length = length - 1;
            if (first == null)
               return rem;
   			first.prev = null;
   			return rem;
   		}
   	}               // remove and return the item from the front

   	public Item removeLast() {
   		if (isEmpty())
   			throw new java.util.NoSuchElementException("Rempving from an empty list");
   		else {
   			Item rem = last.item;
   			//System.out.println("Done1 "+rem);
   			last = last.prev;
            //System.out.println("Done1 "+last);
            length = length - 1;
   			if (last == null) 
               return rem;
   			last.next = null;
   			return rem;
   		}

   	}                // remove and return the item from the end

   	public Iterator<Item> iterator() {
   		return new DequeIterator();
   	}        // return an iterator over items in order from front to end

   	private class DequeIterator implements Iterator<Item> {
   		private Node<Item> current = first;

   		public boolean hasNext() {
   			return current != null;
   		}

   		public Item next() {
   			Item item = current.item;
   			current = current.next;
            //length = length - 1;
   			return item;
   		}
   	}

   	public static void main(String[] args) {
   	  Deque<String> strDeque = new Deque<String>();
         strDeque.addFirst("A");
         System.out.println(strDeque.size());
    strDeque.removeLast();
    System.out.println(strDeque.size());
    System.out.println(0 == strDeque.size());
    System.out.println(true == strDeque.isEmpty());
   	}  // unit testing

}