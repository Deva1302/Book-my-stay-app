import java.util.*;

/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates safe room allocation using Queue, HashMap, and Set
 * to prevent double booking.
 *
 * @author Devanshi
 * @version 6.1
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 1, 100);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 2, 180);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 3, 350);
    }
}

/**
 * Centralized Inventory Service
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {

        int current = inventory.get(roomType);
        inventory.put(roomType, current - 1);
    }

    public void displayInventory() {

        System.out.println("\nRemaining Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " Rooms Available: " + inventory.get(type));
        }
    }
}

/**
 * Reservation Request
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * Booking Request Queue (FIFO)
 */
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/**
 * Booking Service (Room Allocation)
 */
class BookingService {

    private RoomInventory inventory;

    private Set<String> allocatedRoomIDs;

    private HashMap<String, Set<String>> roomAllocations;

    public BookingService(RoomInventory inventory) {

        this.inventory = inventory;

        allocatedRoomIDs = new HashSet<>();

        roomAllocations = new HashMap<>();
    }

    public void processReservations(BookingRequestQueue queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();

            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                String roomID = generateRoomID(type);

                allocatedRoomIDs.add(roomID);

                roomAllocations.putIfAbsent(type, new HashSet<>());
                roomAllocations.get(type).add(roomID);

                inventory.decrementRoom(type);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Assigned Room ID: " + roomID);
                System.out.println("--------------------------");

            } else {

                System.out.println("Reservation Failed for " + r.getGuestName()
                        + " (No rooms available)");
            }
        }
    }

    private String generateRoomID(String type) {

        String id;

        do {

            id = type.substring(0,1).toUpperCase() + (int)(Math.random()*100);

        } while (allocatedRoomIDs.contains(id));

        return id;
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 6.1");
        System.out.println("Use Case 6: Reservation Confirmation & Room Allocation\n");

        RoomInventory inventory = new RoomInventory();

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Double"));
        queue.addRequest(new Reservation("Charlie", "Suite"));
        queue.addRequest(new Reservation("David", "Single"));

        BookingService bookingService = new BookingService(inventory);

        bookingService.processReservations(queue);

        inventory.displayInventory();
    }
}