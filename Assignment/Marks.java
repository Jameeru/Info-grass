package Assignment;
import java.io.*;

public class Marks {
    public static void main(String[] args) {
        String fileName = "marks.csv";

        try {
            FileReader file = new FileReader(fileName);//Reading the file
            BufferedReader reader = new BufferedReader(file);// BufferedReader for simple and fast reading

            String line;// Read each line from the file

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");// Splitting the line into parts through the ,Separator

                if (parts.length == 4) {
                    String name = parts[0].trim(); 
                    int mark1 = Integer.parseInt(parts[1].trim());// Parsing marks and trimming spaces
                    int mark2 = Integer.parseInt(parts[2].trim());
                    int mark3 = Integer.parseInt(parts[3].trim());

                    int total = mark1 + mark2 + mark3;
                    double average = total / 3.0;

                    System.out.println("Name: " + name);
                    System.out.println("Total: " + total);
                    System.out.println("Average: " + average);
                    System.out.println("---------------------");
                } else {
                    System.out.println("Wrong line: " + line);
                }
            }

            reader.close();

        } catch (IOException e) {//
            System.out.println("Could not read the file.");
        } catch (NumberFormatException e) {
            System.out.println("Found invalid number in marks. Check for spaces or symbols.");
        }
    }
}
