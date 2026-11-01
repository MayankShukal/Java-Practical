package practical14;

import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Count Students");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                }

                service.validateAction(choice);

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        service.addStudent(new Student(id, name));
                        System.out.println("Student added successfully");
                        break;

                    case 2:
                        service.displayStudents();
                        break;

                    case 3:
                        System.out.println(
                                "Total Students: " +
                                service.getStudentCount()
                        );
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
