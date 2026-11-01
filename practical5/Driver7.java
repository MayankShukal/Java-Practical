package practical5;
public class Driver7 {

    public static void main(String[] args) {

        Employee[] employees = {

            new FullTime("Rahul", 101, 50000),
            new PartTime("Amit", 102, 80, 200),
            new Intern("Riya", 103, 10000),
            new FullTime("Neha", 104, 60000)

        };

        double total = 0;

        for (Employee e : employees) {

            double salary = e.monthlySalary();

            System.out.println("Name: " + e.name);
            System.out.println("ID: " + e.id);
            System.out.println("Salary: " + salary);

            if (e instanceof Intern) {
                System.out.println("Note: This employee is an intern.");
            }

            total += salary;

            System.out.println();
        }

        System.out.println("Total Payroll: " + total);
    }
}
