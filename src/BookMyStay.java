import java.util.*;

/**
 * Book My Stay App
 * Use Case 8: Booking History & Reporting
 *
 * Demonstrates storing confirmed reservations and generating
 * reports from booking history.
 *
 * @author Devanshi
 * @version 8.0
 */

/**
 * Reservation class
 */
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println(
                "Reservation ID: " + reservationId +
                        ", Guest: " + guestName +
                        ", Room Type: " + roomType
        );
    }
}

/**
 * BookingHistory stores confirmed bookings
 */
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {

        history.add(reservation);

        System.out.println("Reservation added to booking history.");
    }

    public List<Reservation> getReservations() {
        return history;
    }
}

/**
 * BookingReportService generates reports
 */
class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {

        System.out.println("\nBooking History:");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    public void generateSummaryReport(List<Reservation> reservations) {

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {

            String type = r.getRoomType();

            roomCount.put(type, roomCount.getOrDefault(type, 0) + 1);
        }

        System.out.println("\nBooking Summary Report:");

        for (String type : roomCount.keySet()) {
            System.out.println(type + " Rooms Booked: " + roomCount.get(type));
        }

        System.out.println("Total Reservations: " + reservations.size());
    }
}

/**
 * Main Application
 */
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 8.0");
        System.out.println("Use Case 8: Booking History & Reporting\n");

        BookingHistory history = new BookingHistory();

        // Simulated confirmed reservations
        Reservation r1 = new Reservation("RES101", "Alice", "Single");
        Reservation r2 = new Reservation("RES102", "Bob", "Double");
        Reservation r3 = new Reservation("RES103", "Charlie", "Suite");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();

        // Admin views booking history
        reportService.displayAllBookings(history.getReservations());

        // Admin generates summary report
        reportService.generateSummaryReport(history.getReservations());
    }
}