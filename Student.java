
public class Student {

    private int id;
    private String name;
    private double gpa;
    private String major;

    public Student(int id, String name, double gpa, String major) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", GPA: " + gpa
                + ", Major: " + major;
    }
}
