import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] s;
  private int N;

  public RandomizedQueue() {
  	s = (Item[]) new Object[1];
  	N = 0;
  }                 // construct an empty randomized queue

  public boolean isEmpty() {
  	return N == 0;
  }                // is the queue empty?

  public int size() {
  	return N;
  }                       // return the number of items on the queue

  public void enqueue(Item item) {
    if (item == null)
      throw new java.lang.NullPointerException("Adding a null element");
  	if (N == s.length)
  	  resize(2*s.length);
  	s[N++] = item;

  }          // add the item

  private void resize(int capacity) {
  	Item[] sNew = (Item[]) new Object[capacity];
  	for (int i = 0; i < N; i++)
  	  sNew[i] = s[i];  	  
  	s = sNew; 
  }

  public Item dequeue() {
    if (isEmpty())
      throw new java.util.NoSuchElementException("Removing from an Empty List");
  	int index = StdRandom.uniform(N);
  	Item item = s[index];
  	s[index] = s[--N];
  	s[N] = null;
  	if (N > 0 && N == s.length/4)
  	  resize(s.length/2);
  	return item;
  }                   // remove and return a random item

  public Item sample() {
    if (isEmpty())
      throw new java.util.NoSuchElementException("Sampling from an empy list");
  	int index = StdRandom.uniform(N);
  	return s[index];
  }                    // return (but do not remove) a random item

  public Iterator<Item> iterator() {
  	return new RandomizedQueueIterator();
  }        // return an independent iterator over items in random order

  private class RandomizedQueueIterator implements Iterator<Item> {
  	private int ind = 0;

  	public boolean hasNext() {
  	  return ind < N;
  	}

  	public Item next() {
      if (ind >= N)
        throw new java.util.NoSuchElementException("Iterating over empty list");
  	  return s[ind++];
  	}

    public void remove() {
      throw new java.lang.UnsupportedOperationException("remove operation not supported");
    }
  }

  public static void main(String[] args) {
  	RandomizedQueue<Integer> rand = new RandomizedQueue<Integer>();
  	rand.enqueue(1);
  	rand.enqueue(2);
  	rand.enqueue(3);
    rand.dequeue();
  	Iterator<Integer> i = rand.iterator();
  	while (i.hasNext())
  	  System.out.println(i.next());
  }  // unit testing
}
