package Assignment;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    static Map<Integer, RetailCustomer> cmap = new ConcurrentHashMap<>();

    static {
        RetailCustomer rc1 = new RetailCustomer();
        rc1.setName("Vivek");

        RetailCustomer rc2 = new RetailCustomer();
        rc2.setName("Sara");

        cmap.put(1, rc1);
        cmap.put(2, rc2);
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class RetailCustomer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Thread1 extends Thread {
    public void run() {
        for (int index = 3; index <= 102; index++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            RetailCustomer rc = new RetailCustomer();
            rc.setName("John");
            ConcurrentHashMapDemo.cmap.put(index, rc);
        }
    }
}

class Thread2 extends Thread {
    public void run() {
        try {
            Thread.sleep(1000); // Let Thread1 populate the map
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator<Map.Entry<Integer, RetailCustomer>> iter = ConcurrentHashMapDemo.cmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, RetailCustomer> entry = iter.next();
            System.out.println("ID: " + entry.getKey() + " -> Name: " + entry.getValue().getName());
        }
    }
}