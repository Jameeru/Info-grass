package Examples;

public class Outer {
    static class Inner {
        void display() {
            System.out.println("Inside static inner class");
        }
    }

    public static void main(String[] args) {
        Outer.Inner obj = new Outer.Inner();
        obj.display();
    }
}
