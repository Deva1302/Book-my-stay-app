import java.util.*;

/**
 * Book My Stay App
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates attaching optional services to reservations
 * without modifying core booking or inventory logic.
 *
 * @author Devanshi
 * @version 7.1
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
}

/**
 * Represents an optional add-on service
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * Manages add-on services attached to reservations
 */
class AddOnServiceManager {

    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);

        System.out.println("Service added: " + service.getServiceName());
    }

    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services != null) {

            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("\nSelected Services:");

        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " ($" + s.getCost() + ")");
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 7.1");
        System.out.println("Use Case 7: Add-On Service Selection\n");

        // Example reservation (already confirmed in UC6)
        Reservation reservation = new Reservation("RES101", "Alice", "Double");

        // Add-on service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Available services
        AddOnService breakfast = new AddOnService("Breakfast", 20);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 40);
        AddOnService spa = new AddOnService("Spa Access", 60);

        // Guest selects services
        serviceManager.addService(reservation.getReservationId(), breakfast);
        serviceManager.addService(reservation.getReservationId(), spa);

        // Display selected services
        serviceManager.displayServices(reservation.getReservationId());

        // Calculate additional cost
        double totalCost = serviceManager.calculateTotalServiceCost(reservation.getReservationId());

        System.out.println("\nTotal Add-On Cost: $" + totalCost);
    }
}