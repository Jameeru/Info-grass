import java.util.*;

public class Deq {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.addFirst("A");
        deque.addLast("B");
        deque.offerFirst("C");
        deque.offerLast("D");

        System.out.println("Deque: " + deque);

        System.out.println("First: " + deque.peekFirst()); // C

        System.out.println("Last: " + deque.peekLast());   // D

        deque.removeFirst(); // Removes C

        deque.removeLast();//removes D

        System.out.println("After Removals: " + deque); // [A, B]
    }
}
