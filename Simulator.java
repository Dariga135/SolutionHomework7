import java.util.*;
import java.util.concurrent.*;
import java.util.Scanner;

public class Simulator {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Aircraft[] aircrafts = new Aircraft[10];
        for (int i = 0; i < aircrafts.length; i++) {
            int fuelLevel = new Random().nextInt(100);
            if (i % 3 == 0) {
                aircrafts[i] = new PassengerPlane("Passenger " + i, fuelLevel);
            } else if (i % 3 == 1) {
                aircrafts[i] = new CargoPlane("Cargo " + i, fuelLevel);
            } else {
                aircrafts[i] = new Helicopter("Helicopter " + i, fuelLevel);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Request runway for landing");
            System.out.println("2. Request runway for takeoff");
            System.out.println("3. Send MAYDAY (emergency landing)");
            System.out.println("4. Show runway status");
            System.out.println("5. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter aircraft ID for landing: ");
                    String landingId = scanner.next();
                    Aircraft landingAircraft = findAircraftById(landingId, aircrafts);
                    if (landingAircraft != null) {
                        landingAircraft.send("Landing request", tower);
                    } else {
                        System.out.println("Aircraft not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter aircraft ID for takeoff: ");
                    String takeOffId = scanner.next();
                    Aircraft takeOffAircraft = findAircraftById(takeOffId, aircrafts);
                    if (takeOffAircraft != null) {
                        takeOffAircraft.send("Takeoff request", tower);
                    } else {
                        System.out.println("Aircraft not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter aircraft ID to send MAYDAY: ");
                    String maydayId = scanner.next();
                    Aircraft maydayAircraft = findAircraftById(maydayId, aircrafts);
                    if (maydayAircraft != null) {
                        maydayAircraft.send("MAYDAY", tower);
                    } else {
                        System.out.println("Aircraft not found.");
                    }
                    break;

                case 4:
                    tower.processRunwayRequests();
                    break;

                case 5:
                    System.out.println("Exiting simulation...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static Aircraft findAircraftById(String id, Aircraft[] aircrafts) {
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getId().equals(id)) {
                return aircraft;
            }
        }
        return null;
    }
}
