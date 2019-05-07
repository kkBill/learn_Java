package springJDBCTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

    //采用注解的方式进行装配
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int getPriceByBookIsbn(String isbn) {
        String sql = "SELECT price FROM book WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class, isbn);
    }

    /**
     * 更新书的库存，每一次更新，库存减1
     * 需要判断，若当前库存为0，则不执行更新！
     * @param isbn
     */
    @Override
    public void updateBookStock(String isbn) {
        //在更新库存前，先检查库存，如果库存不足，抛出异常
        String sql2 = "SELECT stock FROM book_stock WHERE isbn = ?";
        int stock = jdbcTemplate.queryForObject(sql2,Integer.class, isbn);
        if(stock == 0){
            throw new BookStockExpection("库存不足！");
        }

        String sql = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
        jdbcTemplate.update(sql,isbn);
    }

    @Override
    public void updateAccountBalance(String username, int price) {
        //在更新余额前，先检查，如果余额不足，抛出异常
        String sql2 = "SELECT balance FROM account WHERE username = ?";
        int balance = jdbcTemplate.queryForObject(sql2,Integer.class, username);
        if(balance < price){
            throw new UserAccountExpection("余额不足！");
        }


        String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql,price,username);
    }
}
