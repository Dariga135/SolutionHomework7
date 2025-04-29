import java.util.LinkedList;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeOffQueue = new LinkedList<>();
    private boolean runwayOccupied = false;

    public void broadcast(String msg, Aircraft sender) {
        System.out.println(sender.getId() + " says: " + msg);
        if ("MAYDAY".equals(msg)) {
            emergencyLanding(sender);
        }
    }

    public boolean requestRunway(Aircraft a) {
        if (a.getFuelLevel() < 10) {
            return handleEmergency(a);
        } else {
            if (a instanceof PassengerPlane || a instanceof Helicopter) {
                landingQueue.offer(a);
            } else {
                takeOffQueue.offer(a);
            }
            return false;
        }
    }

    private boolean handleEmergency(Aircraft a) {
        System.out.println(a.getId() + " is an emergency and gets priority landing!");
        if (runwayOccupied) {
            System.out.println("Clearing the runway for emergency landing...");
        }
        runwayOccupied = true;
        return true;
    }

    private void emergencyLanding(Aircraft a) {
        System.out.println("Emergency! " + a.getId() + " needs to land immediately.");
        runwayOccupied = true;
        landingQueue.clear();
        takeOffQueue.clear();
    }

    public void processRunwayRequests() {
        if (runwayOccupied) {
            System.out.println("Runway is currently occupied.");
        } else {
            if (!landingQueue.isEmpty()) {
                Aircraft landingAircraft = landingQueue.poll();
                System.out.println(landingAircraft.getId() + " is landing.");
                runwayOccupied = true;
                runwayOccupied = false;
            } else if (!takeOffQueue.isEmpty()) {
                Aircraft takeOffAircraft = takeOffQueue.poll();
                System.out.println(takeOffAircraft.getId() + " is taking off.");
                runwayOccupied = true;
                runwayOccupied = false;
            }
        }
    }
}
