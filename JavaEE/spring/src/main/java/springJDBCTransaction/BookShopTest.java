package springJDBCTransaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopTest {

    private ApplicationContext ctx = null;
    private BookShopService bookShopService = null;

    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        bookShopService = (BookShopService) ctx.getBean("bookShopService");
    }

    @Test
    public void testBookShopService(){
        bookShopService.purchase("AA","1001");
    }
}
