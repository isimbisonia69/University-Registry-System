
import java.util.*;
import java.util.stream.Collectors;

public class StudentManagement {

    private List<Student> students = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // Register a student
    public boolean registerStudent(Student student) {
        boolean exists = students.stream()
                .anyMatch(s -> s.getId() == student.getId());

        if (exists) {
            System.out.println("This ID already exists.");
            return false;
        }

        students.add(student);
        System.out.println("Student registered successfully!");
        return true;
    }

    // Calculate average marks from the courses map
    public double averageMarks(Student student) {
        return student.getCourses().values()
                .stream()
                .mapToDouble(mark -> mark)
                .average()
                .orElse(0.0);
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        students.stream().forEach(s -> {
            System.out.println(s);
            System.out.println("Average marks: " + averageMarks(s));
            System.out.println("----------------------");
        });
    }

    // Search by ID
    public Student searchById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Task 2.1: Display students with average >= 65
    public void displayAbove65() {
        List<Student> result = students.stream()
                .filter(s -> averageMarks(s) >= 65)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No students have an average of 65 or above.");
            return;
        }

        result.forEach(s -> {
            System.out.println(s.getStudentName()
                    + " - Average: " + averageMarks(s));
        });
    }

    // Task 2.2: Display students with average < 65
    public void displayBelow65() {
        List<Student> result = students.stream()
                .filter(s -> averageMarks(s) < 65)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No students have an average below 65.");
            return;
        }

        result.forEach(s -> {
            System.out.println(s.getStudentName()
                    + " - Average: " + averageMarks(s));
        });
    }

    // Task 2.3: Find students by name
    public void searchByName(String name) {
        List<Student> result = students.stream()
                .filter(s -> s.getStudentName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No student found with that name.");
            return;
        }

        result.forEach(s -> System.out.println(s));
    }

    // Task 2.4: Sort students alphabetically by name
    public void sortByName() {
        List<Student> result = students.stream()
                .sorted(Comparator.comparing(
                        s -> s.getStudentName().toLowerCase()))
                .collect(Collectors.toList());

        result.forEach(s -> System.out.println(s));
    }

    // Task 2.5: Sort by average marks, highest to lowest
    public void sortByAverage() {
        List<Student> result = students.stream()
                .sorted(Comparator.comparingDouble(
                        (Student s) -> averageMarks(s)).reversed())
                .collect(Collectors.toList());

        result.forEach(s -> {
            System.out.println(s.getStudentName()
                    + " - Average: " + averageMarks(s));
        });
        private CourseManagement courseManager = new CourseManagement();
    }

    // Main method: starts the program
    public static void main(String[] args) {
        StudentManagement manager = new StudentManagement();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Register student");
            System.out.println("2. Display all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Display students with average >= 65");
            System.out.println("5. Display students with average < 65");
            System.out.println("6. Search students by name");
            System.out.println("7. Sort students by name");
            System.out.println("8. Sort students by average marks");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = Integer.parseInt(manager.input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(manager.input.nextLine());

                        System.out.print("Enter student name: ");
                        String name = manager.input.nextLine();

                        System.out.print("Enter student age: ");
                        int age = Integer.parseInt(manager.input.nextLine());

                        System.out.print("How many courses? ");
                        int numberOfCourses =
                                Integer.parseInt(manager.input.nextLine());

                        if (numberOfCourses < 0) {
                            System.out.println(
                                    "Number of courses cannot be negative.");
                            break;
                        }

                        Map<String, Double> courses = new HashMap<>();

                        for (int i = 0; i < numberOfCourses; i++) {
                            System.out.print("Enter course name: ");
                            String course = manager.input.nextLine();

                            System.out.print("Enter marks for " + course + ": ");
                            double marks =
                                    Double.parseDouble(manager.input.nextLine());

                            if (marks < 0 || marks > 100) {
                                System.out.println(
                                        "Marks must be between 0 and 100.");
                                i--;
                                continue;
                            }

                            courses.put(course, marks);
                        }

                        System.out.print("How many skills? ");
                        int numberOfSkills =
                                Integer.parseInt(manager.input.nextLine());

                        if (numberOfSkills < 0) {
                            System.out.println(
                                    "Number of skills cannot be negative.");
                            break;
                        }

                        List<String> skills = new ArrayList<>();

                        for (int i = 0; i < numberOfSkills; i++) {
                            System.out.print("Enter skill: ");
                            skills.add(manager.input.nextLine());
                        }

                        Student student = new Student(
                                id, name, age, courses, skills);

                        manager.registerStudent(student);

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid number. Please register again.");
                    }
                    break;

                case 2:
                    manager.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter student ID to search: ");

                    try {
                        int searchId =
                                Integer.parseInt(manager.input.nextLine());

                        Student found = manager.searchById(searchId);

                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Student not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid student ID.");
                    }
                    break;

                case 4:
                    manager.displayAbove65();
                    break;

                case 5:
                    manager.displayBelow65();
                    break;

                case 6:
                    System.out.print("Enter student name to search: ");
                    String searchName = manager.input.nextLine();
                    manager.searchByName(searchName);
                    break;

                case 7:
                    manager.sortByName();
                    break;

                case 8:
                    manager.sortByAverage();
                    break;

                case 9:
                    System.out.println("Goodbye!");
                    manager.input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
