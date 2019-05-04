package generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");

        UserService userService = (UserService) ctx.getBean("userService");//注意bean的命名规则是第一个字小写哦
        userService.add();
    }
}
