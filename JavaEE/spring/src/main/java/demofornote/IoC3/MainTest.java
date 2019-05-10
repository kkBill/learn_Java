package demofornote.IoC3;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("iocwithannotation-config.xml");
        Person person = (Person) ctx.getBean("person");
        person.getCar().drive();
        person.getPet().run();

    }

}
