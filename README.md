# Student Course Registration System

A console-based Java application for managing students, courses, and course registrations, built as the Object-Oriented Programming mini-project at **NIT Srinagar, Department of Information Technology** (3rd Semester, Autumn 2026).

> **Status:** 🚧 In progress. This README describes the planned design and will be updated once the project is complete.

## Team

| Member | Responsibility |
|--------|----------------|
| **Ilha** | Core classes (`Person`, `Student`, `Course`, `Displayable`), Module A (Student Management), Module B (Course Catalogue), `Main.java` console menu |
| **Babar** | `Registration`, `RegistrationException`, Module C (Registration Logic), Module D (Academic Summary), Module E (File Handling) |

## About the Project

Students register for courses offered in a semester. Every registration has to follow academic rules, such as completing prerequisites and staying within a maximum credit limit. The system stores student and course records, tracks registrations, rejects invalid operations with clear messages, and shows each student's registered courses and total credits.

The app runs in the console. It has no GUI and uses no database; all data is saved to text/CSV files.

## Features

### Module A: Student Management
- Add a new student (unique student ID, name, department, semester)
- Prevent duplicate student IDs
- Display all students
- Search for a student by ID
- Display the courses a student has registered for

### Module B: Course Catalogue
- Add a course (code, name, credits, prerequisite code if any, maximum capacity)
- Prevent duplicate course codes
- Display all available courses
- Search for a course by code

### Module C: Course Registration
Before a registration goes through, the system checks:
1. **Student validity:** the student exists
2. **Course validity:** the course exists
3. **Duplicate registration:** the student isn't already registered for the course
4. **Prerequisite completion:** the student has completed the required prerequisite
5. **Credit limit:** total registered credits do not exceed **20**
6. **Course capacity:** the course has seats available

If any check fails, the registration is rejected with a meaningful message (via `RegistrationException`).

### Module D: Student Academic Summary
For a selected student, the summary shows:
- Student ID and name
- Registered courses and the credits for each
- Total registered credits
- Completed prerequisite courses
- Remaining credit allowance

### Module E: File Handling
- Student, course, and registration data are saved to text/CSV files
- Data is loaded when the program starts and saved after relevant changes, so nothing is lost when it exits

## Class Design (Planned)

```
Displayable (interface)
    └── display()

Person (abstract)
    └── Student  ── extends Person, implements Displayable

Course           ── implements Displayable
Registration     ── links a Student to a Course
RegistrationSystem ── coordinates students, courses, registration, dropping
RegistrationException ── custom exception for invalid registrations
FileManager      ── saves/loads data to/from CSV files
Main             ── console menu
```

| Class | Purpose |
|-------|---------|
| `Person.java` | Abstract base class holding common info (ID, name) |
| `Student.java` | Extends `Person`; adds department, semester, completed courses, registered courses |
| `Course.java` | Course code, name, credits, prerequisite, capacity |
| `Displayable.java` | Interface with a `display()` method |
| `Registration.java` | Associates a student with a course |
| `RegistrationSystem.java` | Main business logic and the six registration checks |
| `RegistrationException.java` | User-defined exception for rule violations |
| `FileManager.java` | Persistence using `FileWriter` / `BufferedReader` |
| `Main.java` | Menu-driven console interface |

## OOP Concepts Used

| # | Concept | Where |
|---|---------|-------|
| 1 | Classes and objects | `Student`, `Course`, `Registration`, etc. |
| 2 | Encapsulation | Private fields with getters/setters |
| 3 | Constructors | Parameterised constructors in every model class |
| 4 | Inheritance | `Student extends Person` |
| 5 | Method overriding / runtime polymorphism | `display()` / `toString()` overridden in `Student` and `Course` |
| 6 | Abstract class | `Person` |
| 7 | Interface | `Displayable` |
| 8 | Collections | `ArrayList` and `HashMap` for storing students, courses, registrations |
| 9 | Exception handling | `try-catch` for invalid input and registration errors |
| 10 | User-defined exception | `RegistrationException extends Exception` |
| 11 | File handling | CSV read/write in `FileManager` |
| 12 | Modular design | One responsibility per class; `main()` only drives the menu |

## Menu

```
STUDENT COURSE REGISTRATION SYSTEM
1. Add Student
2. View/Search Students
3. Add Course
4. View/Search Courses
5. Register Student for Course
6. View Student Academic Summary
7. View Students Registered in a Course
8. Save Data
9. Exit
Enter your choice:
```

## How to Run

**Requirement:** JDK 8 or later.

```bash
# Compile
javac *.java

# Run
java Main
```

> _Folder structure and exact commands will be updated once the source code is added._

## Progress

- [ ] `Person.java` (abstract class)
- [ ] `Student.java`
- [ ] `Course.java`
- [ ] `Displayable.java` (interface)
- [ ] Module A: Student Management
- [ ] Module B: Course Catalogue
- [ ] `Main.java` console menu
- [ ] `Registration.java`
- [ ] `RegistrationException.java`
- [ ] Module C: Registration logic (all 6 checks)
- [ ] Module D: Academic Summary
- [ ] `FileManager.java` (Module E)
- [ ] Testing and final README update

---

**Course:** Object-Oriented Programming · **Session:** Autumn 2026 · **NIT Srinagar, Department of IT**
