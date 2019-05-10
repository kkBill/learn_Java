package springJDBCTransaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopTest {

    private ApplicationContext ctx = null;
    private BookShopService bookShopService = null;
    private Cashier cashier = null;

    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        bookShopService = (BookShopService) ctx.getBean("bookShopService");
        cashier = (Cashier) ctx.getBean("cashier");
    }

    @Test
    public void testBookShopService(){
        bookShopService.purchase("AA","1001");
    }


}
