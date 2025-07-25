import java.util.*;

public class Q1 {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.offer("Chennai");
        queue.offer("Mumbai");
        queue.offer("Delhi");
        System.out.println("Queue after offer(): " + queue);

        queue.add("Kolkata");
        System.out.println("Queue after add(): " + queue);

        System.out.println("peek(): " + queue.peek());

        // element() in try-catch: throws exception if queue is empty
        try {
            System.out.println("element(): " + queue.element());
        } catch (NoSuchElementException e) {
            System.out.println("element(): Queue is empty!");
        }

       //return null
        System.out.println("poll(): " + queue.poll());
        System.out.println("Queue after poll(): " + queue);

        // remove() throws exception if queue is empty
        try {
            System.out.println("remove(): " + queue.remove());
        } catch (NoSuchElementException e) {
            System.out.println("remove(): Queue is empty!");
        }
        System.out.println("Queue after remove(): " + queue);

        System.out.println("Is queue empty? " + queue.isEmpty());

        System.out.println("Size of queue: " + queue.size());

        queue.clear();
        System.out.println("Queue after clear(): " + queue);

        // peek()  empty queue  returns null
        System.out.println("peek() on empty queue: " + queue.peek());

        // poll()  empty queue  returns null
        System.out.println("poll() on empty queue: " + queue.poll());

        //  element() and remove()  empty queue again
        try {
            System.out.println("element() on empty queue: " + queue.element());
        } catch (NoSuchElementException e) {
            System.out.println("element() on empty queue: Queue is empty!");
        }

        try {
            System.out.println("remove() on empty queue: " + queue.remove());
        } catch (NoSuchElementException e) {
            System.out.println("remove() on empty queue: Queue is empty!");
        }
    }
}
