package practical3;
public class Driver1 {

    public static void main(String[] args) {

        Card[] cards = new Card[5];

        Card[] input = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Diamonds"),
            new Card("Ace", "Spades"),
            new Card("Jack", "Clubs")
        };

        int count = 0;

        for (int i = 0; i < input.length; i++) {

            boolean found = false;

            for (int j = 0; j < count; j++) {

                if (input[i].equals(cards[j])) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Duplicate found: " + input[i]);
                break;
            }

            cards[count] = input[i];
            count++;
        }
    }
}
