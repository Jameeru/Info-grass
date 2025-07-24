package Examples;

final class FinalClass {
    void display() {
        System.out.println("Final class method");
    }
}

// cannot inherit from final FinalClass // 

public class FinalExample {
    public static void main(String[] args) {
        new FinalClass().display();
    }
}
//coz main method creates instance of final class//
