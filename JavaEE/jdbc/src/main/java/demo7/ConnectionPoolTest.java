package demo7;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    public void testC3P0WithConfigXML() throws Exception{
        ComboPooledDataSource cpds = new ComboPooledDataSource("testc3p0");
        Connection connection = cpds.getConnection();

        System.out.println(cpds.getMinPoolSize());
        System.out.println(cpds.getMaxPoolSize());

    }

    @Test
    public void testC3P0() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/mypracticedb?serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("1234");

        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }


    @Test
    public void testDBCP() throws SQLException {
        BasicDataSource dataSource = null;
        dataSource = new BasicDataSource();

        //设置数据库连接池的必要属性
        dataSource.setUrl("jdbc:mysql://localhost:3306/mypracticedb?serverTimezone=UTC");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        Connection connection = dataSource.getConnection();
    }
}
