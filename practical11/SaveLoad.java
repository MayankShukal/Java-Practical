package practical11;


import java.io.*;

class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;
    String name;
    double marks;

    transient String password;

    Student(int id, String name, double marks, String password) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.password = password;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class SaveLoad {

    public static void main(String[] args) {

        Student[] students = {
            new Student(101, "Mayank", 85.5, "abc123"),
            new Student(102, "Rahul", 90.0, "xyz456"),
            new Student(103, "Amit", 78.5, "pass789")
        };

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream("students.dat"));

            out.writeObject(students);
            out.close();

            System.out.println("Objects saved successfully.\n");

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream("students.dat"));

            Student[] loadedStudents =
                    (Student[]) in.readObject();

            in.close();

            System.out.println("Objects loaded successfully.\n");

            for (Student student : loadedStudents) {
                student.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

