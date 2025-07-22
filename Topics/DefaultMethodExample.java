package Topics;


interface Vehicle {
    default void print() {
        System.out.println("I am a vehicle!");
    }
}

class Car implements Vehicle {
    // No need to override print()
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }
}

