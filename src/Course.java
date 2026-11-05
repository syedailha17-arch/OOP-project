public class Course implements Displayable {

    // Fields
    private String courseCode;
    private String courseName;
    private int credits;
    private String prerequisiteCode;
    private int maxCapacity;
    private int enrolledCount;

    // Constructor
    public Course(String courseCode, String courseName, int credits, String prerequisiteCode, int maxCapacity) {
        // calling setters so validation also runs when the object is created
        setCourseCode(courseCode);
        setCourseName(courseName);
        setCredits(credits);
        setPrerequisiteCode(prerequisiteCode);
        setMaxCapacity(maxCapacity);
        this.enrolledCount = 0; // no setter: only changed via increment/decrement
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getPrerequisiteCode() {
        return prerequisiteCode;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    // Setters with validation
    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            System.out.println("Error: Course code cannot be empty!");
            return;
        }
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            System.out.println("Error: Course name cannot be empty!");
            return;
        }
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        if (credits <= 0) {
            System.out.println("Error: Credits must be greater than 0!");
            return;
        }
        this.credits = credits;
    }

    public void setPrerequisiteCode(String prerequisiteCode) {
        // can be null so no validation needed
        this.prerequisiteCode = prerequisiteCode;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            System.out.println("Error: Capacity must be greater than 0!");
            return;
        }
        this.maxCapacity = maxCapacity;
    }

    // Methods
    public boolean hasAvailableSeat() {
        return enrolledCount < maxCapacity;
    }

    public void incrementEnrolled() {
        if (enrolledCount < maxCapacity) {
            enrolledCount++;
        } else {
            System.out.println("Error: Course is already full!");
        }
    }

    public void decrementEnrolled() {
        if (enrolledCount > 0) {
            enrolledCount--;
        } else {
            System.out.println("Error: No students enrolled!");
        }
    }

    // display() from Displayable interface(diff file)
    @Override
    public void display() {
        System.out.println("====================");
        System.out.println("Course Code  : " + courseCode);
        System.out.println("Course Name  : " + courseName);
        System.out.println("Credits      : " + credits);
        System.out.println("Prerequisite : " + (prerequisiteCode == null ? "None" : prerequisiteCode));
        System.out.println("Capacity     : " + enrolledCount + "/" + maxCapacity);
        System.out.println("====================");
    }
}
