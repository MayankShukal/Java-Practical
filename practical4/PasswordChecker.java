package practical4;

public class PasswordChecker {

    public static String strength(String pw) {

        int count = 0;

        boolean length = pw.length() >= 8;
        boolean uppercase = pw.matches(".*[A-Z].*");
        boolean digit = pw.matches(".*[0-9].*");
        boolean special = pw.matches(".*[^a-zA-Z0-9].*");

        if (length) {
            count++;
            System.out.println("Length >= 8: Passed");
        } else {
            System.out.println("Length >= 8: Failed");
        }

        if (uppercase) {
            count++;
            System.out.println("Uppercase: Passed");
        } else {
            System.out.println("Uppercase: Failed");
        }

        if (digit) {
            count++;
            System.out.println("Digit: Passed");
        } else {
            System.out.println("Digit: Failed");
        }

        if (special) {
            count++;
            System.out.println("Special character: Passed");
        } else {
            System.out.println("Special character: Failed");
        }

        if (count <= 1) {
            return "Weak";
        } else if (count <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}
