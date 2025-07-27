package Assignment;
import java.util.*;


/*
REC     VIKRAM 53 46 89                 Hash map , Array list , Streams , Aggregate Function
VIT     RAJ    83 82 89
VIT     PRABHU 89 76 45
REC     NILA   96 89 76
VIT     YUVRAJ 89 76 90
SRM     DHONI  89 56 98

1) Students having more than 60% average that stored in list then print it.
2) Students in VIT who have more than 160 that will be stored in list then print it .


 */
import java.util.stream.*;

public class Student {
    String name;
    int m1, m2, m3;

    public Student(String name, int m1, int m2, int m3) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }


    public String toString() {

        return name + " " + m1 + " " + m2 + " " + m3;

    }

    public static void main(String[] args) {
        Student s1 = new Student("VIKRAM", 53, 46, 89);
        Student s2 = new Student("RAJ", 10, 20, 89);
        Student s3 = new Student("PRABHU", 89, 76, 45);
        Student s4 = new Student("NILA", 96, 89, 76);
        Student s5 = new Student("YUVRAJ", 89, 76, 90);
        Student s6 = new Student("DHONI", 89, 56, 98);

        Map<String, List<Student>> map = new HashMap<>();
        map.put("REC", List.of(s1, s4));
        map.put("VIT", List.of(s2, s3, s5));
        map.put("SRM", List.of(s6));

        System.out.println(map);


        //  Students with average > 60
        List<Student> avg = map.values().stream()
                .flatMap(List::stream)
                .filter(s -> IntStream.of(s.m1, s.m2, s.m3).average().getAsDouble() > 60)
                .collect(Collectors.toList());

        System.out.println("Students with average > 60%:");
        avg.forEach(System.out::println);

        // VIT students with total > 160
        List<Student> vit160 = map.getOrDefault("VIT", new ArrayList<>()).stream()
                .filter(s -> IntStream.of(s.m1, s.m2, s.m3).sum() > 160)
                .collect(Collectors.toList());

        System.out.println("\nVIT students with total marks > 160:");
        vit160.forEach(System.out::println);

    }
}
