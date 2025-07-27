import java.util.*;

public class Q1 {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.add("Chennai");
        queue.add("Mumbai");
        queue.add("Delhi");
        System.out.println("Queue after add(): " + queue);

        queue.add("Kolkata");
        System.out.println("Queue after add(): " + queue);

        System.out.println("peek(): " + queue.peek());//Top view


        System.out.println("poll(): " + queue.poll());//delete the peek element

        System.out.println("Queue after poll(): " + queue);

        System.out.println("Is queue empty? " + queue.isEmpty());//Boolean

        System.out.println("Size of queue: " + queue.size());//gives the size of queue

        queue.clear();

        System.out.println("Queue after clear(): " + queue);

        System.out.println("peek() on empty queue: " + queue.peek());


        System.out.println("poll() on empty queue: " + queue.poll());

    }
}
