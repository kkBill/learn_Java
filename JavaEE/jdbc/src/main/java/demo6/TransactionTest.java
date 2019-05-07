package demo6;

import demo4.JDBCTools;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionTest {

    @Test
    public void testTransaction() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = JDBCTools.getConnection();

            //开始事务：在开始前需要先取消默认提交
            connection.setAutoCommit(false);

            String sql = "UPDATE bank SET balance = balance - 500 WHERE id = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            int i = 10 / 0;//模仿异常

            sql = "UPDATE bank SET balance = balance + 500 WHERE id = 2";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            //结束事务，提交
            connection.commit();

        } catch(Exception e1) {
            e1.printStackTrace();

            //如果出现异常，那就要在此处回滚事务
            try {
                connection.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally{
            JDBCTools.releaseDB(null,preparedStatement, connection);
        }

    }

}
