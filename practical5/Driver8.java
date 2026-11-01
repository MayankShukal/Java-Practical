package practical5;

public class Driver8{

    public static void main(String[] args) {

        Media[] items = {

            new Book("Java Programming", 3),
            new DVD("Avengers", 5),
            new Magazine("Science Today", 2),
            new Book("Data Structures", 4)

        };

        double total = 0;

        for (Media m : items) {

            double fee = m.lateFee();

            System.out.println("Title: " + m.title);
            System.out.println("Late Days: " + m.lateDays);
            System.out.println("Late Fee: " + fee);

            total += fee;

            System.out.println();
        }

        System.out.println("Total Late Fees: " + total);
    }
}
