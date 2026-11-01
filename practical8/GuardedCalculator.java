package practical8;


import java.util.Scanner;

class DivideByZeroException extends Exception {

    DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static double calculate(double a, double b, char op)
            throws DivideByZeroException {

        switch (op) {
            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new DivideByZeroException(
                            "Cannot divide by zero!");
                }
                return a / b;

            default:
                throw new IllegalArgumentException(
                        "Invalid operator!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean success = false;

        while (!success) {

            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                char op = sc.nextLine().charAt(0);

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                double result = calculate(a, b, op);

                System.out.println("Result: " + result);

                success = true;

            } catch (NumberFormatException e) {

                System.out.println("Error: Please enter valid numbers.");

            } catch (DivideByZeroException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (StringIndexOutOfBoundsException e) {

                System.out.println("Error: Please enter an operator.");

            } finally {

                System.out.println("Calculation attempt completed.\n");
            }
        }

        System.out.println("Calculator finished.");

        sc.close();
    }
}

