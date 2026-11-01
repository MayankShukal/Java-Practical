package practical14;

import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null || student.getName() == null
                || student.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid student");
        }

        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(
                    student.getId() + " - " + student.getName()
            );
        }
    }

    public void validateAction(int choice) {
        if (choice < 1 || choice > 3) {
            throw new IllegalArgumentException("Invalid menu action");
        }
    }
}
