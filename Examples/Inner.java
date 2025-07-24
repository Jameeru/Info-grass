package Examples;

public class Inner {
    class Nested {
        void message() {
            System.out.println("Inner class method");
        }
    }

    public static void main(String[] args) {
        Inner outer = new Inner();// Create outer class instance
        Inner.Nested inner = outer.new Nested();// Use outer instance to create inner
        inner.message();// Call method
    }
}


