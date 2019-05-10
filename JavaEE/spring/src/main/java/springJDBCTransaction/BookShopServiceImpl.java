package springJDBCTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;




    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void purchase(String username, String isbn){
        //1. 根据ISBN获取书的价格
        int price = bookShopDao.getPriceByBookIsbn(isbn);

        //2. 书的库存余额减1
        bookShopDao.updateBookStock(isbn);

        //3. 账户余额减去书的价格
        bookShopDao.updateAccountBalance(username, price);
    }
}
