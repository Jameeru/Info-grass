package Topics;

class Parent {
    final void display() {
        System.out.println("This method cannot be overridden.");
    }
}

class Child extends Parent {
    // Attempting to override would cause compile error
}

public class FinalMethodExample {
    public static void main(String[] args) {
        Child child = new Child();
        child.display();
    }
}
