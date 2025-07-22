package Examples;

interface Greeting {
    void sayHello();
}

public class LambdaExample {
    public static void main(String[] args) {
        Greeting greet = () -> System.out.println("Hello!");
        greet.sayHello();
    }
}
