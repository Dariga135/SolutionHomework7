public class Helicopter extends Aircraft {
    public Helicopter(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    public void receive(String msg) {
        System.out.println(id + " received: " + msg);
    }
}
