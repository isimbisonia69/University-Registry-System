import java.util.*;

public class StudentManagements {

    private List<Student> students = new ArrayList<>();

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

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        students.stream().forEach(s -> System.out.println(s));
    }

    public Student searchById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        StudentManagement manager = new StudentManagement();

        // We will add your menu here.
    }
}