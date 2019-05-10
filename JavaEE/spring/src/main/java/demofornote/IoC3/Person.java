package demofornote.IoC3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    @Autowired
    private Pet pet;
    @Autowired
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
