package Examples;


public class FinalVarExample {
    final int value = 100;

    public static void main(String[] args) {
        FinalVarExample obj = new FinalVarExample();
        // obj.value = 200; // Error
        System.out.println("Final value: " + obj.value);
    }
}
