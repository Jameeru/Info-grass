package Examples;

final class FinalClass {
    void display() {
        System.out.println("Final class method");
    }
}

// class Child extends FinalClass {} // 

public class FinalExample {
    public static void main(String[] args) {
        new FinalClass().display();
    }
}

