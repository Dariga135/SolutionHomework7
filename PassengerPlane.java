public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    public void receive(String msg) {
        System.out.println(id + " received: " + msg);
    }
}
