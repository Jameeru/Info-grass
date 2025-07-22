package Examples;

public class StaticVarExample {
    static int count = 0;

    StaticVarExample() {
        count++;
        System.out.println("Count: " + count);
    }

    public static void main(String[] args) {
        new StaticVarExample();
        new StaticVarExample();
    }
}


