package Examples;

abstract class Animal {
    abstract void sound();
}

public class AnonymousClass {
    public static void main(String[] args) {
        Animal a = new Animal() {
            void sound() {
                System.out.println("Roar!");
            }
        };
        a.sound();
    }
}

