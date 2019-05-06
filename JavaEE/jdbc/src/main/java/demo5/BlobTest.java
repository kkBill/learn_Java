package demo5;

import demo4.JDBCTools;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlobTest {

    @Test
    public void testInsertBlobFile() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO user(username, password, picture) VALUES(?,?,?)";
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "kobe");
            preparedStatement.setString(2, "2408");

            InputStream inputStream = new FileInputStream("D:\\workspace\\javashop\\learn_Java\\JavaEE\\jdbc\\src\\main\\resources\\kobe-bryant.jpg");
            preparedStatement.setBlob(3, inputStream);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(null, preparedStatement, connection);
        }
    }

    @Test
    public void testGetBlobFile() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT picture FROM user WHERE username = '" + "kobe'";
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob picture = resultSet.getBlob("picture");
                //输入输出流
                InputStream in = picture.getBinaryStream();
                OutputStream out = new FileOutputStream("kobe-copy.jpg");

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.close();
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(resultSet, preparedStatement, connection);
        }
    }


}
