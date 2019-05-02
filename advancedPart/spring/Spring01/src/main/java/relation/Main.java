package relation;

import autowired.Address;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-autowired.xml");

        Address address = (Address) ctx.getBean("address");
        System.out.println(address);

        address = (Address) ctx.getBean("address2");//address2继承与前面那个address
        System.out.println(address);

        address = (Address) ctx.getBean("address3");
        System.out.println(address);
    }

}
