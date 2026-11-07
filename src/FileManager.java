import java.io.*;
import java.util.ArrayList;
public class FileManager {
    public static void saveCourses(ArrayList<Course> courses){
        try(FileWriter fw = new FileWriter("data/courses.csv")){
            for(Course c : courses){
                String prereq = (c.getPrerequisiteCode() == null)? "" : c.getPrerequisiteCode();
                fw.write(c.getCourseCode() + "," + c.getCourseName() + "," + c.getCredits() + "," + prereq + "," + c.getMaxCapacity() + "\n");
            }
        }catch(IOException e){
            System.out.println("error saving: " + e.getMessage());
        }
    }

    public static ArrayList<Course> loadCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        if(!new File("data/courses.csv").exists()){
            return courses;
        }
        try(BufferedReader br = new BufferedReader(new FileReader("data/courses.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",", -1);
                int credits = Integer.parseInt(parts[2]);
                int capacity = Integer.parseInt(parts[4]);
                String prereq = parts[3].isEmpty() ? null : parts[3];
                courses.add(new Course(parts[0], parts[1], credits, prereq, capacity));
            }
        }catch(IOException e){
            System.out.println("couldnt read the file: " + e.getMessage());
        }
        return courses;
    }

    public static void saveStudents(ArrayList<Student> students){
        try(FileWriter fw = new FileWriter("data/students.csv")){
            for(Student s : students){
                String completed = String.join(";", s.getCompletedCourses());
                fw.write(s.getId() + "," + s.getName() + "," + s.getDepartment() + "," + s.getSemester() + "," + completed + "\n");
            }
        }catch(IOException e){
            System.out.println("error saving the file: " + e.getMessage());
        }
    }

    public static ArrayList<Student> loadStudents(){
        ArrayList<Student> students = new ArrayList<>();
        if(!new File("data/students.csv").exists()){
            return students;
        }
        try(BufferedReader br = new BufferedReader(new FileReader("data/students.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("," , -1);
                int semester = Integer.parseInt(parts[3]);
                Student s = new Student(parts[0], parts[1], parts[2], semester);
                if(!parts[4].isEmpty()){
                    String[] codes = parts[4].split(";");
                    for(String code : codes){
                        s.addCompletedCourse(code);
                    }
                }
                students.add(s);
            }
        }catch(IOException e){
            System.out.println("error loading the file: " + e.getMessage());
        }
        return students;
    }

    public static void saveRegistrations(ArrayList<Registration> registrations){
        try(FileWriter fw = new FileWriter("data/registrations.csv")){
            for(Registration r : registrations){
                fw.write(r.toCSV() + "\n");
            }
        }catch(IOException e){
            System.out.println("error saving the file: " + e.getMessage());
        }
    }

    public static void loadRegistrations(RegistrationSystem rs){
        if(!new File("data/registrations.csv").exists()){
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader("data/registrations.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                rs.restoreRegistration(parts[0], parts[1]);
            }
        }catch(IOException e){
            System.out.println("error loading the file: " + e.getMessage());
        }
    }
}
