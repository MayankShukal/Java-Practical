
package practical2;

class CinemaShow {

    private String title;
    private int seatsAvailable;
    private final int capacity;

    private static int totalBooked = 0;

    CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    CinemaShow(String title) {
        this(title, 100);
    }

    boolean book(int n) {
        if (n > 0 && n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        } else {
            return false;
        }
    }

    void cancel(int n) {
        int bookedSeats = capacity - seatsAvailable;

        if (n > 0 && n <= bookedSeats) {
            seatsAvailable += n;
            totalBooked -= n;
        }
    }

    int getSeatsAvailable() {
        return seatsAvailable;
    }

    static int getTotalBooked() {
        return totalBooked;
    }
}

public class CinemaShowMain {

    public static void main(String[] args) {

        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println("Movie: Avengers");
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nBooking 10 seats: " + show.book(10));
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nBooking 20 seats: " + show.book(20));
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nBooking 30 seats: " + show.book(30));
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nCancelling 5 seats");
        show.cancel(5);
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nBooking 5 seats: " + show.book(5));
        System.out.println("Available Seats: " + show.getSeatsAvailable());

        System.out.println("\nTotal Booked: " + CinemaShow.getTotalBooked());
    }
}
