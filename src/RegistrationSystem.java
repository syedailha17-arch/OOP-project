import java.util.ArrayList;

public class RegistrationSystem {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Registration> registrations;
    private static final int MAX_CREDITS = 24;

    public RegistrationSystem(ArrayList<Student> students, ArrayList<Course> courses){
        this.students = students;
        this.courses = courses;
        registrations = new ArrayList<>();
    }

    public Student findStudent(String id){
        for(Student s : students){
            if(s.getId().equals(id))
                return s;
        }
        return null;
    }

    public Course findCourse(String code){
        for(Course c : courses){
            if(c.getCourseCode().equals(code))
                return c;
        }
        return null;
    }

    public int getEnrolledCount(String courseCode){
        int count = 0;
        for(Registration r : registrations){
            if(r.getCourseCode().equals(courseCode))
                count += 1;
        }
        return count;
    }

    public int getTotalCredits(Student s){
        int total = 0;
        for(Course c : s.getRegisteredCourses()){
            total += c.getCredits();
        }
        return total;
    }
}
