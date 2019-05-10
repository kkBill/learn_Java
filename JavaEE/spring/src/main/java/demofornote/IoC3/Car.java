package demofornote.IoC3;

import org.springframework.stereotype.Component;

@Component
public class Car {
    public void drive() {
        System.out.println("car is driving...");
    }
}
