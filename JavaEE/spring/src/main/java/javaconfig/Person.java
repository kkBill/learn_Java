package javaconfig;

public class Person {
    private Pet pet;
    private Car car;

    public Person() {
    }

    public Person(Pet pet, Car car) {
        this.pet = pet;
        this.car = car;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Pet getPet() {
        return pet;
    }

    public Car getCar() {
        return car;
    }
}
