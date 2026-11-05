import java.util.ArrayList;

public class Student extends Person implements Displayable {

    private String department;
    private int semester;
    private ArrayList<String> completedCourses;
    private ArrayList<Course> registeredCourses;

    // Constructor calling each setter
    public Student(String id, String name, String department, int semester) {
        super(id, name);
        // calling setters so validation also runs when the object is created
        setDepartment(department);
        setSemester(semester);
        this.completedCourses = new ArrayList<>();
        this.registeredCourses = new ArrayList<>();
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public int getSemester() {
        return semester;
    }

    public ArrayList<String> getCompletedCourses() {
        return completedCourses;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Setters with validation
    public void setDepartment(String department) {
        if (department == null || department.isEmpty()) {
            System.out.println("Error: Department cannot be empty!");
            return;
        }
        this.department = department;
    }

    public void setSemester(int semester) {
        if (semester < 1 || semester > 8) {
            System.out.println("Error: Semester must be between 1 and 8!");
            return;
        }
        this.semester = semester;
    }

    // Methods for completedCourses
    public void addCompletedCourse(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            System.out.println("Error: Course code cannot be empty!");
            return;
        }
        if (!completedCourses.contains(courseCode)) {
            completedCourses.add(courseCode);
        }
    }

    public boolean hasCompletedCourse(String courseCode) {
        return completedCourses.contains(courseCode);
    }

    // Methods for registeredCourses
    public void addRegisteredCourse(Course course) {
        if (course == null) {
            System.out.println("Error: Course cannot be null!");
            return;
        }
        if (registeredCourses.contains(course)) {
            System.out.println("Error: Already registered for " + course.getCourseCode() + "!");
            return;
        }
        registeredCourses.add(course);
    }

    public void removeRegisteredCourse(Course course) {
        registeredCourses.remove(course);
    }

    // Get total credits of all registered courses
    public int getTotalCredits() {
        int total = 0;
        for (Course course : registeredCourses) {
            total += course.getCredits();
        }
        return total;
    }

    // Override display() from Person
    @Override
    public void display() {
        System.out.println("====================");
        System.out.println("Student ID   : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Department   : " + department);
        System.out.println("Semester     : " + semester);
        System.out.println("Total Credits: " + getTotalCredits());
        System.out.println("====================");
    }
}