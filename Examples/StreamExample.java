package Examples;

import java.util.*;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // forEach
        nums.forEach(n -> System.out.print(n + " "));

        // sum using stream
        int sum = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println("\nSum: " + sum);
    }
}

