import java.util.ArrayList;

public class RegistrationSystem {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Registration> registrations;
    private static final int MAX_CREDITS = 20;

    public RegistrationSystem(ArrayList<Student> students, ArrayList<Course> courses){
        this.students = students;
        this.courses = courses;
        registrations = new ArrayList<>();
    }

    public ArrayList<Registration> getRegistrations(){
        return registrations;
    }

    public void restoreRegistration(String studentId, String courseCode){
        Student s = findStudent(studentId);
        Course c = findCourse(courseCode);
        if(s == null || c == null){
            return;
        }
        registrations.add(new Registration(studentId, courseCode));
        s.addRegisteredCourse(c);
        c.incrementEnrolled();
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

        int newTotal = s.getTotalCredits() + c.getCredits();
        if(newTotal > MAX_CREDITS)
            throw new RegistrationException("you have chosen: " + newTotal + " credits, maximum limit for credits: " + MAX_CREDITS);

        if(getEnrolledCount(courseCode) >= c.getMaxCapacity())
            throw new RegistrationException("the course: " + courseCode + " is already full");

        registrations.add(new Registration(studentId, courseCode));
        s.addRegisteredCourse(c);
        c.incrementEnrolled();
        System.out.println("Registered " + studentId + " for " + courseCode);
    }

    public void dropCourse(String studentId, String courseCode) throws RegistrationException{
        Student s = findStudent(studentId);
        if(s==null)
            throw new RegistrationException("Student not found: " + studentId);

        Course c = findCourse(courseCode);
        if(c==null)
            throw new RegistrationException("Course not found: " + courseCode);

        Registration found = null;
        for(Registration r : registrations){
            if(r.getStudentId().equals(studentId) && r.getCourseCode().equals(courseCode)){
                found = r;
                break;
            }
        }
        if(found == null){
            throw new RegistrationException("not registered for " + courseCode);
        }
        registrations.remove(found);
        s.removeRegisteredCourse(c);
        c.decrementEnrolled();

        System.out.println("student: " + studentId + " has dropped from course: " + courseCode);
    }

    public void showAcademicSummary(String studentId) throws RegistrationException{
        Student s = findStudent(studentId);
        if(s==null)
            throw new RegistrationException("Student not found: " + studentId);

        System.out.println("===== Academic Summary =====");
        System.out.println(s.getName() + " -> " + s.getId());

        if(s.getRegisteredCourses() == null || s.getRegisteredCourses().isEmpty())
            System.out.println("no courses registered");

        else{
            System.out.println("Registered courses: ");
            for(Course rc : s.getRegisteredCourses())
                System.out.println(rc.getCourseCode() + " , " + rc.getCourseName() + " , " + rc.getCredits());
        }
        System.out.println("Total credits: " + s.getTotalCredits());
        System.out.println("Courses completed: " + s.getCompletedCourses());
        System.out.println("Remaining credits: " + (MAX_CREDITS - s.getTotalCredits()));
    }
}
