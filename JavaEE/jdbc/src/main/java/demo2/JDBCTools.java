package demo2;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 封装JDBC的数据库连接和异常处理
 * version 1
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
    public static void myUpdate(String sql) throws Exception{
        Connection connection = null;
        Statement statement = null;

        try{
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseStatementAndConnection(statement,connection);
        }
    }

    //查询，并返回结果
//    public static ResultSet myQuery(String sql) throws Exception{
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try{
//            connection = getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            if(resultSet == null)
//                System.out.println("JDBCTools no result...");
//            return resultSet;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }



    //异常处理、释放资源
    public static void releaseStatementAndConnection(Statement statement,
                                                     Connection connection) throws Exception{
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

    public static void releaseResultSetAndStatementAndConnection(ResultSet resultSet,
                                                                 Statement statement,
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
