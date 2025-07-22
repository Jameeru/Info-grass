package Topics;

import java.util.Arrays;
import java.util.List;

public class ForEachExample {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        fruits.forEach(fruit -> System.out.println(fruit));
    }
}

