package Assignment;
import java.util.*;
import java.util.stream.Collectors;

public class Dup {
    public static void main(String[] args) {

        ArrayList<String> file = new ArrayList<>();
        file.add("Aadhar.pdf");
        file.add("license.pdf");
        file.add("pan.pdf");
        file.add("pan.pdf");
        file.add("photo.pdf");

        System.out.println(file);

        Set<String> duplicates = file.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {

            System.out.println("Duplicates found: " + duplicates);

        } else {
            System.out.println("No duplicates found.");
        }



    }

}
