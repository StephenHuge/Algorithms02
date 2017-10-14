import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args)
    {
        int count = StdIn.readInt();
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
            if (s == null)  break;
        }
        for(int i = 0; i < count; i++) {
            System.out.println(rq.dequeue());
        }
    }
 }
