import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args)
    {
        String str = args[0];
        int count = Integer.parseInt(str);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
//        In in = new In(args[2]);
//        while (!in.isEmpty()) {
//            String s = in.readString();
//            rq.enqueue(s);
//            if (s == null)  break;
//        }
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
            if (s == null)  break;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(rq.dequeue());
        }
    }
 }
