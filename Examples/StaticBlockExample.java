package Examples;

public class StaticBlockExample {
    static {
        System.out.println("Static block executed before main()");
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
    }
}

