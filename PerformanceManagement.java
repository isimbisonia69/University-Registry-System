
import java.util.*;
import java.util.stream.Collectors;

public class PerformanceManagement {

    // Calculate average marks
    private double averageMarks(Student student) {
        return student.getCourses().values().stream()
                .mapToDouble(mark -> mark)
                .average()
                .orElse(0.0);
    }

    // Task 4: Generate performance report for every student
    public void generateReport(List<Student> students) {

        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        for (Student student : students) {
            DoubleSummaryStatistics stats =
                    student.getCourses().values().stream()
                            .mapToDouble(mark -> mark)
                            .summaryStatistics();

            System.out.println("\nStudent: " + student.getStudentName());
            System.out.println("Average marks: " + stats.getAverage());
            System.out.println("Highest mark: " + stats.getMax());
            System.out.println("Lowest mark: " + stats.getMin());
            System.out.println("Total marks: " + stats.getSum());
            System.out.println("Number of courses: " + stats.getCount());
            System.out.println("------------------------");
        }

        // Identify student with highest average
        students.stream()
                .max(Comparator.comparingDouble(this::averageMarks))
                .ifPresent(s -> System.out.println(
                        "Highest average: " + s.getStudentName()
                        + " - " + averageMarks(s)));

        // Count students who passed (average >= 50)
        long passed = students.stream()
                .filter(s -> averageMarks(s) >= 50)
                .count();

        System.out.println("Number of students who passed: " + passed);

        // Display top three students
        System.out.println("\nTop three students:");

        students.stream()
                .sorted(Comparator.comparingDouble(
                        (Student s) -> averageMarks(s)).reversed())
                .limit(3)
                .forEach(s -> System.out.println(
                        s.getStudentName()
                        + " - Average: " + averageMarks(s)));
    }
}

