package Topics;

public class StaticVariableExample {
    static int count = 0;

    StaticVariableExample() {
        count++;
    }

    public static void main(String[] args) {
        new StaticVariableExample();
        new StaticVariableExample();
        System.out.println("Instances created: " + count);
    }
}

