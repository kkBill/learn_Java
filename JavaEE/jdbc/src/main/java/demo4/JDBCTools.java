package demo4;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 封装JDBC的数据库连接和异常处理
 * version 2
 */

public class JDBCTools {

    //获取数据库连接
    public static Connection getConnection() throws Exception {

        //读取类路径下的jdbc.properties配置文件
        InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);

        String driver = properties.getProperty("driver");
        String jdbcurl = properties.getProperty("jdbcurl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        //加载数据驱动程序（注册驱动）不懂？
        Class.forName(driver);

        //通过getConnection方法获取连接
        Connection connection = DriverManager.getConnection(jdbcurl, user, password);

        return connection;
    }

    //执行SQL 更新方法（包括insert、delete、update）
    //这里调用的是PreparedStatement，不确定传入的sql字段有几个，故采用不定参数的形式
    public static void myUpdate(String sql, Object ... objects) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //通过for循环填入参数
            for(int i = 0; i < objects.length; i++){
                //注意，setXXX()方法的下标是从1开始的
                //因为不知道传入的参数是什么类型，因此调用setObject()方法
                preparedStatement.setObject(i+1, objects[i]);
            }
            //执行更新操作
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseDB(null,preparedStatement,connection);
        }
    }

    //异常处理、释放资源
    public static void releaseDB(ResultSet resultSet, Statement statement,
                                 Connection connection) throws Exception{
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(statement != null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(connection != null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
