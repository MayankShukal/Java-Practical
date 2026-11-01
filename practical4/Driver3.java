package practical4;
public class Driver3
 {

    public static void main(String[] args) {

        String[] passwords = {
            "abc",
            "Abcd1234!",
            "hello123",
            "Password"
        };

        for (String pw : passwords) {

            System.out.println("\nPassword: " + pw);

            String result = PasswordChecker.strength(pw);

            System.out.println("Strength: " + result);
        }
    }
}
