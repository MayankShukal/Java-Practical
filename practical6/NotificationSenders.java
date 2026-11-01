package practical6;

public class NotificationSenders {

    @FunctionalInterface
    interface Notifier {
        void send(String message);
    }

    interface Urgent {
    }

    public static void main(String[] args) {

        Notifier emailSender = message ->
                System.out.println("Email: " + message);

        Notifier smsSender = (Notifier & Urgent) message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {
            emailSender,
            smsSender
        };

        String message = "Your assignment deadline is tomorrow!";

        System.out.println("Broadcasting message:");

        for (Notifier sender : senders) {
            sender.send(message);
        }

        System.out.println("\nSending urgent notifications again:");

        for (Notifier sender : senders) {
            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}
