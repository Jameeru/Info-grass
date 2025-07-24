package Examples;

abstract class Animal {
    abstract void sound();// Abstract method with no implementation
}

public class AnonymousClass {
    public static void main(String[] args) {
        Animal a = new Animal() {//creating an anonymous subclass of animal
            void sound() {
                System.out.println("Roar!");
            }
        };
        a.sound();
    }
}

