package Topics;

import java.util.ArrayList;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("James");

        // Lambda to print each name
        names.forEach(name -> System.out.println(name));
    }
}

