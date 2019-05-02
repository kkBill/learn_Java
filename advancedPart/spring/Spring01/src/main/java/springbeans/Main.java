package springbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);

        person =(Person) ctx.getBean("person2");
        System.out.println(person);

        Album ab = (Album)ctx.getBean("album");
        System.out.println(ab);

        ab = (Album)ctx.getBean("album2");
        System.out.println(ab);
    }
}
