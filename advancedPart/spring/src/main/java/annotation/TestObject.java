package annotation;

import annotation.controller.UserController;
import annotation.repository.UserRepository;
import annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestObject {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");

//        TestObject to = (TestObject) ctx.getBean("testObject");
//        System.out.println(to);

//        UserController userController = (UserController) ctx.getBean("userController");
//        System.out.println(userController);

        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        System.out.println(userRepository);

        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService);

    }

}
