public class Registration {
    private String studentId;
    private String courseCode;

    public Registration(String studentId, String courseCode){
        this.studentId = studentId;
        this.courseCode = courseCode;
    }
    public String getStudentId(){
        return studentId;
    }
    public String getCourseCode(){
        return courseCode;
    }

    @Override 
    public String toString(){
        return studentId + " -> " + courseCode;
    }

    public String toCSV(){
        return studentId + "," + courseCode;
    }

}
