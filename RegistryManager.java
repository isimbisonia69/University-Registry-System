
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegistryManager {

    private List<Student> students = new ArrayList<>();

    public void loadStudentsFromFile(String fileName) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length != 4) {
                    System.out.println("Invalid student record: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    double gpa = Double.parseDouble(data[2].trim());
                    String major = data[3].trim();

                    Student student =
                            new Student(id, name, gpa, major);

                    students.add(student);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in line: " + line);
                }
            }

            System.out.println("Students loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error reading the file: "
                    + e.getMessage());
        }
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        students.forEach(System.out::println);
    }

    public void groupStudentsByMajor() {

        if (students.isEmpty()) {
            System.out.println("No students available to group.");
            return;
        }

        Map<String, List<String>> groupedStudents =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getMajor,
                                TreeMap::new,
                                Collectors.mapping(
                                        Student::getName,
                                        Collectors.toList()
                                )
                        ));

        groupedStudents.forEach((major, names) -> {
            System.out.println("\nMajor: " + major);

            names.forEach(name ->
                    System.out.println("- " + name));
        });
    }
}

