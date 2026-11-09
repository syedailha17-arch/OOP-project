import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static RegistrationSystem system;

    public static void main(String[] args) {

        students.addAll(FileManager.loadStudents());
        courses.addAll(FileManager.loadCourses());
        system = new RegistrationSystem(students, courses);
        FileManager.loadRegistrations(system);

        int choice;

        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewSearchStudents();
                case 3 -> addCourse();
                case 4 -> viewSearchCourses();
                case 5 -> registerStudentForCourse();
                case 6 -> viewAcademicSummary();
                case 7 -> viewStudentsInCourse();
                case 8 -> saveData();
                case 9 -> dropCourse();
                case 10 -> {
                    saveData();
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 10);

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n=============================");
        System.out.println(" STUDENT REGISTRATION SYSTEM");
        System.out.println("=============================");
        System.out.println("1. Add Student");
        System.out.println("2. View/Search Students");
        System.out.println("3. Add Course");
        System.out.println("4. View/Search Courses");
        System.out.println("5. Register Student for Course");
        System.out.println("6. View Academic Summary");
        System.out.println("7. View Students in a Course");
        System.out.println("8. Save Data");
        System.out.println("9. Drop Course");
        System.out.println("10. Exit");
        System.out.println("=============================");
    }

    static void addStudent() {
        System.out.println("\n--- Add Student ---");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        if (system.findStudent(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();

        System.out.print("Enter Semester (1-8): ");
        int semester = Integer.parseInt(scanner.nextLine().trim());

        Student student = new Student(id, name, dept, semester);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    static void viewSearchStudents() {
        System.out.println("\n--- View/Search Students ---");
        System.out.println("1. View all students");
        System.out.println("2. Search by ID");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        if (choice == 1) {
            if (students.isEmpty()) {
                System.out.println("No students found!");
                return;
            }
            for (Student s : students) {
                s.display();
            }
        } else if (choice == 2) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine().trim();
            Student s = system.findStudent(id);
            if (s == null) {
                System.out.println("Student not found!");
            } else {
                s.display();
                System.out.println("Registered Courses:");
                if (s.getRegisteredCourses().isEmpty()) {
                    System.out.println("No courses registered yet.");
                } else {
                    for (Course c : s.getRegisteredCourses()) {
                        c.display();
                    }
                }
            }
        }
    }

    static void addCourse() {
        System.out.println("\n--- Add Course ---");

        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine().trim();

        if (system.findCourse(code) != null) {
            System.out.println("Error: Course with code " + code + " already exists!");
            return;
        }

        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Prerequisite Code (or press Enter if none): ");
        String prereq = scanner.nextLine().trim();
        if (prereq.isEmpty()) prereq = null;

        System.out.print("Enter Max Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        Course course = new Course(code, name, credits, prereq, capacity);
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    static void viewSearchCourses() {
        System.out.println("\n--- View/Search Courses ---");
        System.out.println("1. View all courses");
        System.out.println("2. Search by course code");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        if (choice == 1) {
            if (courses.isEmpty()) {
                System.out.println("No courses found!");
                return;
            }
            for (Course c : courses) {
                c.display();
            }
        } else if (choice == 2) {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine().trim();
            Course c = system.findCourse(code);
            if (c == null) {
                System.out.println("Course not found!");
            } else {
                c.display();
            }
        }
    }

    static void registerStudentForCourse() {
        System.out.println("\n--- Register Student for Course ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine().trim();

        try {
            system.registerStudent(studentId, courseCode);
        } catch (RegistrationException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    static void viewAcademicSummary() {
        System.out.println("\n--- Academic Summary ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        try {
            system.showAcademicSummary(studentId);
        } catch (RegistrationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewStudentsInCourse() {
        System.out.println("\n--- Students in a Course ---");
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine().trim();

        Course c = system.findCourse(courseCode);
        if (c == null) {
            System.out.println("Course not found!");
            return;
        }

        System.out.println("Students registered in " + c.getCourseName() + ":");
        boolean found = false;
        for (Student s : students) {
            for (Course rc : s.getRegisteredCourses()) {
                if (rc.getCourseCode().equals(courseCode)) {
                    s.display();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No students registered in this course yet.");
        }
    }

    static void dropCourse() {
        System.out.println("\n--- Drop Course ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine().trim();

        try {
            system.dropCourse(studentId, courseCode);
        } catch (RegistrationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void saveData() {
        boolean ok = FileManager.saveStudents(students)
          & FileManager.saveCourses(courses)
          & FileManager.saveRegistrations(system.getRegistrations());

        if (ok) {
            System.out.println("Data saved successfully!");
        } else {
            System.out.println("Data was not saved!");
        }
    }
}