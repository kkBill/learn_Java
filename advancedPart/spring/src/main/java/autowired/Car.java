package autowired;

public class Car {
    private String brand;
    private double price;

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    Car(){
        System.out.println("Car constructor...");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
