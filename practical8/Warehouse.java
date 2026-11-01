package practical8;


import java.util.*;

class OutOfStockException extends Exception {

    private int shortfall;

    OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {

    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {

    private Map<String, Integer> stock = new HashMap<>();

    Warehouse() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Keyboard", 3);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero.");
        }

        if (!stock.containsKey(item)) {
            throw new OutOfStockException(
                    "Item not available: " + item, qty);
        }

        int available = stock.get(item);

        if (qty > available) {

            int shortfall = qty - available;

            throw new OutOfStockException(
                    "Insufficient stock for " + item,
                    shortfall);
        }

        stock.put(item, available - qty);

        System.out.println(
                "Issued " + qty + " " + item + "(s) successfully.");
    }

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        String[] items = {
            "Laptop", "Mouse", "Keyboard", "Laptop", "Tablet"
        };

        int[] quantities = {
            2, 15, 0, 5, 3
        };

        System.out.println("Processing Stock Requests:\n");

        for (int i = 0; i < items.length; i++) {

            try {

                warehouse.issue(items[i], quantities[i]);

            } catch (InvalidQuantityException e) {

                System.out.println("Invalid Quantity: "
                        + e.getMessage());

            } catch (OutOfStockException e) {

                System.out.println("Stock Error: "
                        + e.getMessage());

                System.out.println("Shortfall: "
                        + e.getShortfall());
            }

            System.out.println();
        }

        System.out.println("All requests processed.");
    }
}

