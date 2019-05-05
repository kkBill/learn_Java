package cycle;

public class Car {
    private String brand;

    Car(){
        System.out.println("Car's constructor...");
    }

    public void setBrand(String brand) {
        System.out.println("Car's setBrand...");
        this.brand = brand;
    }

    public void myInit(){
        System.out.println("Car's myInit...");
    }

    public void myDestroy(){
        System.out.println("Car's myDestroy...");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
