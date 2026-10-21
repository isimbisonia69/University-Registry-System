
import java.util.*;

public class Student {
    private int id;
    private String studentName;
    private int age;
    private Map<String, Double> courses = new HashMap<>();
    private List<String> skills = new ArrayList<>();

    // Constructor
    public Student(int id, String studentName, int age,
                   Map<String, Double> courses, List<String> skills) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.courses = courses;
        this.skills = skills;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getAge() {
        return age;
    }

    public Map<String, Double> getCourses() {
        return courses;
    }

    public List<String> getSkills() {
        return skills;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourses(Map<String, Double> courses) {
        this.courses = courses;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    // toString()
    @Override
    public String toString() {
        return "Student ID: " + id +
               "\nName: " + studentName +
               "\nAge: " + age +
               "\nCourses and marks: " + courses +
               "\nSkills: " + skills;
    }

    // Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Register student");
            System.out.println("2. View all students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                System.out.print("Enter student's ID: ");
                int id = input.nextInt();
                input.nextLine();

                System.out.print("Enter student's name: ");
                String studentName = input.nextLine();

                System.out.print("Enter student's age: ");
                int age = input.nextInt();
                input.nextLine();

                Map<String, Double> courses = new HashMap<>();

                System.out.print("How many courses? ");
                int numberOfCourses = input.nextInt();
                input.nextLine();

                for (int i = 0; i < numberOfCourses; i++) {
                    System.out.print("Enter course name: ");
                    String course = input.nextLine();

                    System.out.print("Enter marks: ");
                    double marks = input.nextDouble();
                    input.nextLine();

                    courses.put(course, marks);
                }

                List<String> skills = new ArrayList<>();

                System.out.print("How many skills? ");
                int numberOfSkills = input.nextInt();
                input.nextLine();

                for (int i = 0; i < numberOfSkills; i++) {
                    System.out.print("Enter skill: ");
                    String skill = input.nextLine();
                    skills.add(skill);
                }

                Student student = new Student(
                    id, studentName, age, courses, skills
                );

                students.add(student);

                System.out.println("Student registered successfully!");

            } else if (choice == 2) {
                if (students.isEmpty()) {
                    System.out.println("No students registered yet.");
                } else {
                    System.out.println("\n===== REGISTERED STUDENTS =====");

                    for (Student s : students) {
                        System.out.println(s);
                        System.out.println("-------------------------------");
                    }
                }

            } else if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        input.close();
    }
}
