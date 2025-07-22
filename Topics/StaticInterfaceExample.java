package Topics;

interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
}

public class StaticInterfaceExample {
    public static void main(String[] args) {
        int result = MathUtils.add(5, 3);
        System.out.println("Sum: " + result);
    }
}

