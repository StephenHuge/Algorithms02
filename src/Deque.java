

import java.io.File;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;
    private Iterator<Item> iterator;

    public Deque()                           // construct an empty deque
    {    
//        iterator = new MyIterator();
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return size() == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        validateAdd(item);
        if (head == null)
        {
            head = new Node();
            head.item = item;
            tail = head;
        } else {
            Node oldHead = head;
            head = new Node();
            head.item = item;
            head.next = oldHead;
        }
    }
    public void addLast(Item item)           // add the item to the end
    {
        validateAdd(item);
        if (tail == null)
        {
            tail = new Node();
            tail.item = item;
            head = tail;
        } else {
            Node oldTail = tail;
            tail = new Node();
            tail.item = item;
            oldTail.next = tail;
        }
    }

    /**
     * if input parameter {@code item} is null, then throw a IllegalArgumentException.
     */
    private void validateAdd(Item item) 
    {
        if (item == null)   throw new java.lang.IllegalArgumentException();
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        validateRemove();
        Item item = head.item;
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;   // new head's last node must be null
        }
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        validateRemove();
        Item item = tail.item;
        if (tail.last == null) {
            tail = null;
            head = null;
        } else {
            tail = tail.last;
            tail.next = null;   // new tail's next node must be null
        }
        return item;
    }
    /**
     * if head or tail node is null, which means this deque is empty, 
     * remove operation is not admitted. 
     */
    private void validateRemove() 
    {
        if (head == null || tail == null)   throw new java.util.NoSuchElementException();
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        if (iterator == null) {
            iterator = new MyIterator();
        }
        return iterator;
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<String> deque = new Deque<>();
        
//        In in = new In(new File("src/tinyTale.txt"));      // input file
        In in = new In(new File("src/mediumTale.txt"));      // input file
        
        while (!in.isEmpty()) {
            String item = in.readString();
            if (item == null)    break;
//            System.out.println("Input: " + item);
//            deque.addLast(item);
            deque.addFirst(item);
        }
        Iterator<String> it = deque.iterator();
        System.out.println("Iterating...");
//        System.out.println(it.hasNext());
        while(it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println("Iterated.");
        
    }
    /**
     * a inner class for Deque, this is a iterator that implements interface Iterator<>.
     * 
     * @author HJS
     * 
     * @date 2017年10月14日
     * 
     */
    private class MyIterator implements Iterator<Item> {
        private Node current = head;
        @Override
        public boolean hasNext() {
            if (current == null) return false;
            return true;
        }
        @Override
        public Item next() {
            if (current == null)   throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    /**
     * a inner class Node for Deque
     * @author HJS
     * 
     * @date 2017年10月14日
     * 
     */
    private class Node {
        private Item item = null;
        private Node last = null;
        private Node next = null;
    }
}