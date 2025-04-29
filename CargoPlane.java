public class CargoPlane extends Aircraft {
    public CargoPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    public void receive(String msg) {
        System.out.println(id + " received: " + msg);
    }
}
