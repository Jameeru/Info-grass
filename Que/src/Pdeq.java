import java.util.PriorityQueue;
import java.util.Collections;

public class Pdeq {
    public static void main(String[] args) {
       
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        q1.add(1);
        q1.add(3);
        q1.add(2);

        // PriorityQueue with reverse order (q2)
        PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
        q2.add(1);
        q2.add(3);
        q2.add(2);

        System.out.println("q1 (ascending):");
        while (!q1.isEmpty()) {
            System.out.print(q1.poll() + " "); // 1, 2, 3
        }
        System.out.println("\nq2 (descending):");
        while (!q2.isEmpty()) {
            System.out.print(q2.poll() + " "); // 3, 2, 1
        }
    }
}
