package Collections;

import java.util.ArrayList;

// Java Program Example to Demonstrate
// Addition, Deletion and Updation of Element

class Array {
    public static void main(String args[]){

        // Creating an Array of string type
        ArrayList<String> al = new ArrayList<>();

        // 1. Addition

        // Adding elements to ArrayList
        al.add("Python");
        al.add("Java");

        System.out.println("Orignal List : "+al);

        // Adding Elements through index
        al.add(1, "For");

        System.out.println("After Adding element at index 1 : "+ al);

        // 2. Delete Element

        // remove element using index
        al.remove(0);

        System.out.println("Element removed from index 0 : "+ al);

        // Remove Element  the value
        al.remove("Python");

        System.out.println("Element Geeks removed : "+ al);

        // 3. Updating Values

        // Update  value at index 0
        al.set(0, "Ruby");


        // Printing all the elements in an ArrayList
        System.out.println("List after updation of value : "+al);
    }
}