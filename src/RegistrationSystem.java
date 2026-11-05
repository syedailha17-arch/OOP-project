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

    public void registerStudent(String studentId, String courseCode) throws RegistrationException{
        Student s = findStudent(studentId);
        if(s==null)
            throw new RegistrationException("Student not found: " + studentId);

        Course c = findCourse(courseCode);
        if(c==null)
            throw new RegistrationException("Course not found: " + courseCode);

        for(Course rc : s.getRegisteredCourses()){
            if(rc.getCourseCode().equals(courseCode))
                throw new RegistrationException("Already registered for " + courseCode);
        }

        String prereq = c.getPrerequisiteCode();
        if(prereq != null && !prereq.isEmpty()){
            if(!s.getCompletedCourses().contains(prereq)){
                throw new RegistrationException("Prerequisite " + prereq + " not completed for " + courseCode);
            }
        }

        int limit = getTotalCredits(s) + c.getCredits();
        if(limit > MAX_CREDITS)
            throw new RegistrationException("you have chosen: " + limit + " credits, maximum limit for credits: " + MAX_CREDITS);

        if(getEnrolledCount(courseCode) >= c.getMaxCapacity())
            throw new RegistrationException("the course: " + courseCode + " is already full");
    }
}
