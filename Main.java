
import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File(
                "C:\\Users\\HP\\OneDrive\\Documents\\JAVA\\students.txt"
        );

        RegistryManager manager = new RegistryManager();
        manager.loadStudentsFromFile(file.getAbsolutePath());

        System.out.println("\n===== ALL STUDENTS =====");
        manager.displayAllStudents();

        System.out.println("\n===== STUDENTS GROUPED BY MAJOR =====");
        manager.groupStudentsByMajor();
    }
}

