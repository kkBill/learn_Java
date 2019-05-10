package javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public Car car(){
        return new Car();
    }

    @Bean
    public Pet pet(){
        return new Pet();
    }

    @Bean
    public Person person(){
        return new Person(pet(),car());
    }
}
