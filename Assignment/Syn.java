package Assignment;

import java.util.ArrayList;

public class Syn {
    public static void main(String[] args) {
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Chennai");
        cities.add("Mumbai");
        cities.add("Delhi");
        cities.add("Karnataka");

        Thread t1 = new Thread(() -> {
            cities.set(1, "Bombay");
        });

        Thread t2 = new Thread(() -> {
            cities.set(1, "Mumbai");
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Final cities list: " + cities);
    }
}

