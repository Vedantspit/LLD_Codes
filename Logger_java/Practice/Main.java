public class Main {
    public static void main(String[] args) {
        Vehicle v = new ElectricCar("TESLA");
        v.start();
        v.move();

        ElectricCar c = (ElectricCar) v;
        c.toraMaiKe();
    }
}
