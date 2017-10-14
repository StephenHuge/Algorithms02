import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;
    private Iterator<Item> iterator;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        items = (Item[]) new Object[16];
    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return size() == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return size;
    }
    /**
     * resize the array with length capacity
     */
    private void resize(int capacity) 
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) 
        {
            copy[i] = items[i];
        }
        items = copy;
    }
    public void enqueue(Item item)           // add the item
    {
        validateAdd(item);
        if (size() == items.length)  resize(2 * items.length);
        items[size++] = item;
    }
    /**
     * if input parameter {@code item} is null, then throw a IllegalArgumentException.
     */
    private void validateAdd(Item item)
    {
        if (item == null)    throw new java.lang.IllegalArgumentException();
    }
    public Item dequeue()                    // remove and return a random item
    {
        validateGet();
        int random = random();
        Item temp = items[random];
        if (random != size() - 1)    items[random] = items[size() - 1];  // to keep array whole    
        items[size() - 1] = null; 
        size--;
        if (size() < items.length / 4)     resize(items.length / 2);
        return temp;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        validateGet();
        return items[random()];
    }
    /**
     * get a random number for 0 to size - 1
     */
    private int random() 
    {
        int random = StdRandom.uniform(size());
        //        System.out.println("random number: " + random);
        return random;
    }
    /**
     * to validate whether size of array is 0 when sample() or dequeue() is called,
     * if it is, throw a exception
     */
    private void validateGet() {
        if (size() == 0) throw new java.util.NoSuchElementException();
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        iterator = new MyIterator(items, size());
        return iterator;
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        //      In in = new In(new File("src/tinyTale.txt"));      // input file
//        In in = new In(new File("src/mediumTale.txt"));      // input file

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String item = in.readString();
            if (item == null)    break;
            System.out.println("Input: " + item);
            rq.enqueue(item);
        }
        //        int size = rq.size();
        //        for (int i = 0; i < size; i++) 
        //        {
        //            System.out.print(rq.dequeue() + " ");
        ////            rq.dequeue();
        ////            System.out.println("size is " + rq.size());
        //        }
        Iterator<String> it = rq.iterator();
        System.out.println("Iterating...");
        System.out.println(it.hasNext());
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println("Iterated.");
    }
    /**
     * a inner class for RandomizedQueue, this is a iterator that implements interface Iterator<>.
     * 
     * @author HJS
     * 
     * @date 2017-10-14
     * 
     */
    private class MyIterator implements Iterator<Item> {
        private Item[] copy = null;    // copy items as a new array
        private int count = 0;
        public MyIterator(Item[] items, int count)
        {
            this.copy = items.clone();
            this.count = count;
        }
        @Override
        public boolean hasNext() {
            return count != 0;
        }
        @Override
        public Item next() {
            if (count == 0)  throw new java.util.NoSuchElementException();

            int random = StdRandom.uniform(count);
            Item temp = copy[random];           
            if (temp != copy[count - 1])    copy[random] = copy[count - 1];
            copy[count - 1] = null;
            count--;
            return temp;
        }
        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}