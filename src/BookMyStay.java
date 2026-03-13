/**
 * =========================================================
 * MAIN CLASS – UseCase2RoomInitialization
 * =========================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This program introduces object modeling using abstraction
 * and inheritance. Different room types are modeled using
 * an abstract Room class and concrete subclasses.
 *
 * Availability is represented using simple static variables
 * rather than data structures.
 *
 * @version 2.1
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double size;
    protected double price;

    public Room(String roomType, int beds, double size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price per night: $" + price);
    }
}

/* ---------------- SINGLE ROOM ---------------- */

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 80);
    }
}

/* ---------------- DOUBLE ROOM ---------------- */

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 120);
    }
}

/* ---------------- SUITE ROOM ---------------- */

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 250);
    }
}

/* ========================================================= */

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("        Welcome to Book My Stay");
        System.out.println("      Hotel Booking Management App");
        System.out.println("              Version 2.1");
        System.out.println("========================================\n");

        /* Room objects */

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        /* Static availability variables */

        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        System.out.println("----- Room Details & Availability -----\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailability);
        System.out.println("---------------------------------------\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailability);
        System.out.println("---------------------------------------\n");

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
        System.out.println("---------------------------------------\n");

        System.out.println("Application terminated successfully.");
    }
}