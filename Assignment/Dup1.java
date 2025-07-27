package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dup1 {
    public static void main(String[] args) {
        ArrayList<String> file = new ArrayList<>();
        file.add("Aadhar.pdf");
        file.add("license.pdf");
        file.add("pan.pdf");
        file.add("pan.pdf");
        file.add("photo.pdf");

        System.out.println(file);

        Set<String> duplicates = new HashSet<>();
        for (String name : file) {
            if (Collections.frequency(file, name) > 1) {
                System.out.println("Duplicate found");
            }

        }
        System.out.println("Duplicates: " + duplicates);

    }

    }

