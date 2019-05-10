package demofornote.IoC3;

import org.springframework.stereotype.Component;

@Component
public class Pet {
    public void run() {
        System.out.println("pet is running...");
    }
}
