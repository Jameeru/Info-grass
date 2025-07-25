import java.util.*;

public class Sta1 {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();


        stack.push("Chennai");
        stack.push("Mumbai");
        stack.push("Delhi");
        System.out.println("Stack after push(): " + stack);


        System.out.println("Top element (peek()): " + stack.peek());


        System.out.println("Popped element: " + stack.pop());
        
        System.out.println("Stack after pop(): " + stack);


        int position = stack.search("Chennai");
        System.out.println("Position of 'Chennai': " + position);


        System.out.println("Is stack empty? " + stack.isEmpty());


        System.out.println("Size of stack: " + stack.size());


        stack.clear();
        System.out.println("Stack after clear(): " + stack);



    }
}
