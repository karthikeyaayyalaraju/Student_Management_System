import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    String course;

    Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Course: " + course;
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                sc.next(); // clear invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        int id = getValidatedInt("Enter ID (numbers only): ");
        String name = getValidatedString("Enter Name (letters only): ");
        String course = getValidatedString("Enter Course (letters only): ");

        students.add(new Student(id, name, course));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("List of Students:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void updateStudent() {
        int id = getValidatedInt("Enter Student ID to update: ");
        for (Student s : students) {
            if (s.id == id) {
                s.name = getValidatedString("Enter New Name: ");
                s.course = getValidatedString("Enter New Course: ");
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void deleteStudent() {
        int id = getValidatedInt("Enter Student ID to delete: ");
        for (Student s : students) {
            if (s.id == id) {
                students.remove(s);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Utility method for integer input
    static int getValidatedInt(String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                sc.nextLine(); // consume newline
                return number;
            } else {
                System.out.println("Invalid number! Try again.");
                sc.nextLine(); // clear invalid input
            }
        }
    }

    // Utility method for string input (must be letters, not empty or digits only)
    static String getValidatedString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            } else if (input.matches("\\d+")) {
                System.out.println("Input cannot be numbers only. Try again.");
            } else {
                return input;
            }
        }
    }
}
