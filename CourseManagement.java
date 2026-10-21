
import java.util.*;
import java.util.stream.Collectors;

public class CourseManagement {

    public void displayAllCourses(List<Student> students) {
        students.stream()
                .flatMap(s -> s.getCourses().entrySet().stream()
                        .map(entry -> s.getStudentName()
                                + " - " + entry.getKey()
                                + " - Marks: " + entry.getValue()))
                .sorted()
                .forEach(System.out::println);
    }

    public void displayUniqueCourses(List<Student> students) {
        students.stream()
                .flatMap(s -> s.getCourses().keySet().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public void searchByCourse(List<Student> students, String courseName) {
        students.stream()
                .filter(s -> s.getCourses().keySet().stream()
                        .anyMatch(course ->
                                course.equalsIgnoreCase(courseName)))
                .forEach(s -> System.out.println(s.getStudentName()));
    }

    public void displayUniqueSkills(List<Student> students) {
        students.stream()
                .flatMap(s -> s.getSkills().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public void groupStudentsByCourse(List<Student> students) {
        Map<String, List<Student>> groups = students.stream()
                .flatMap(s -> s.getCourses().keySet().stream()
                        .map(course ->
                                new AbstractMap.SimpleEntry<String, Student>(
                                        course, s)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        TreeMap::new,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.toList())));

        groups.forEach((course, courseStudents) -> {
            System.out.println("\nCourse: " + course);

            courseStudents.forEach(s ->
                    System.out.println(" - " + s.getStudentName()));
        });
    }
}
