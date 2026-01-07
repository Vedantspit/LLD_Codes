public abstract class Car implements Vehicle {
    protected String brand;

    Car(String brand) {
        this.brand = brand;
        System.out.println(" Car constructor called ");
    }

    @Override
    public void start() {
        System.out.println(brand + " car started");

    }

    public abstract void fuelType();
}