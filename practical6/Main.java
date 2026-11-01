package practical6;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(500.0, 1200.0, 2500.0);

        System.out.println("Original Prices: " + prices);

        System.out.println("\nChoose Discount Rule:");
        System.out.println("1. 10% Discount");
        System.out.println("2. Flat Rs. 100 Discount");
        System.out.println("3. 20% Discount above Rs. 1000, otherwise 5%");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        switch (choice) {
            case 1:
                rule = price -> price * 0.90;
                break;

            case 2:
                rule = price -> Math.max(0, price - 100);
                break;

            case 3:
                rule = price -> price > 1000
                        ? price * 0.80
                        : price * 0.95;
                break;

            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        List<Double> discountedPrices =
                DiscountEngine.applyDiscount(prices, rule);

        System.out.println("\nDiscounted Prices:");

        for (double price : discountedPrices) {
            System.out.println("Rs. " + price);
        }

        sc.close();
    }
}
