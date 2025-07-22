package Examples;

public class DemoApp {
    public static void main(String[] args) {
        try {
            int divideByZero = 5 / 0;  // Code that may raise exception
            System.out.println("Rest of code in try block");
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException => " + e.getMessage());
        }
    }
}

