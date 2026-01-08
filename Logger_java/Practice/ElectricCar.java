public class ElectricCar extends Car {
    public ElectricCar(String brand) {
        super(brand);
        System.out.println("ElectricCar constructor called");
    }

    @Override
    public void move() {
        System.out.println(brand + " car is moving Silently");
    }

    @Override
    public void fuelType() {
        System.out.println("Fuel type: Electricity");
    }

    public void toraMaiKe() {
        System.out.println("GTA RP TBONE");

    }
}
