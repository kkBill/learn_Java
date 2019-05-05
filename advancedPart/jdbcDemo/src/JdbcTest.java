import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcTest {

    /**
     * 测试用最原始的JDBC方法来连接数据库
     */
    @Test
    public void testDriver() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/mypracticedb?serverTimezone=UTC";
        Properties info = new Properties();
        info.put("user","root");
        info.put("password","1234");
        Connection connection = driver.connect(url,info);

        System.out.println(connection);
    }

    /**
     * 通过配置文件来读取数据库的必要信息（driver/url/user/password），并连接数据库
     */
    public Connection getConnection() throws Exception {

        //读取类路径下的jdbc.properties配置文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);

        String driver = properties.getProperty("driver");
        String jdbcurl = properties.getProperty("jdbcurl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        //加载数据驱动程序（注册驱动）不懂？？？？
        /**
         * 通过DriverManager有什么好处呢？就是可以同时加载多个数据库驱动
         * 比如，我还可以定义一个Oracle的驱动，只要在配置文件中配好，不妨令其为driver2，也就可以使用了
         */
        //DriverManager.registerDriver(Class.forName(driver).newInstance());
        Class.forName(driver);

        //通过getConnection方法获取连接
        Connection connection = DriverManager.getConnection(jdbcurl, user, password);

        return connection;
    }

    /**
     * 测试利用JDBC对数据表进行更新操作（Insert/Update/Delete）
     */
    @Test
    public void testStatement() throws Exception{
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "insert into student values('1001', 'linda', '女', 25)";
            //String sql = "delete from student where ID='1004'";
            //String sql = "update student set Age=19 where Name='linda'";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //这里写得比较丑陋，对于这种非业务性的代码，应该封装成一个工具类以供使用
            try {
                if(statement != null)
                    statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    /**
     * 测试利用JDBC对数据库进行查询操作，并读取查询结果
     * ResultSet：结果集，封装了使用JDBC进行查询的结果
     */
    @Test
    public void testResultSet(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = getConnection();
            statement = connection.createStatement();
            String sql = "select * from student";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                //String ID = resultSet.getString("ID");
                int ID = resultSet.getInt(1);
                String Name = resultSet.getString("Name");
                String Sex = resultSet.getString("Sex");
                String Age = resultSet.getString("Age");
                System.out.println("ID: "+ID+" Name: "+Name+" Sex: "+Sex+" Age: "+Age);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //TODO
            //关闭处理
        }
    }
}
