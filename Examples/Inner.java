package Examples;

public class Inner {
    class Nested {
        void message() {
            System.out.println("Inner class method");
        }
    }

    public static void main(String[] args) {
        Inner outer = new Inner();
        Inner.Nested inner = outer.new Nested();
        inner.message();
    }
}


