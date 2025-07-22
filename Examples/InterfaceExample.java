package Examples;

interface MyInterface {
    default void show() {
        System.out.println("Default method");
    }

    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

class Impl implements MyInterface {}

public class InterfaceExample {
    public static void main(String[] args) {
        Impl obj = new Impl();
        obj.show(); // default method
        MyInterface.staticMethod(); // static method
    }
}

