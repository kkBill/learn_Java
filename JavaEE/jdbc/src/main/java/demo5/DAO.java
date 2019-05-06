package demo5;

import demo4.JDBCTools;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.Expression;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//利用JDBC编写DAO
public class DAO {

    //INSERT UPDATE DELETE
    public void update(String sql, Object... args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //建立连接
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            //填充字段
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //执行操作
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(null, preparedStatement, connection);
        }
    }

    //查询一条记录，返回对应的对象
    //难点+重点
    public <T> T get(Class<T> cl, String sql, Object... args) throws Exception {
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            //1. 获取连接
            connection = JDBCTools.getConnection();

            //2. 获取PreparedStatement
            preparedStatement = connection.prepareStatement(sql);

            //3. 填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //4. 进行查询，得到ResultSet
            resultSet = preparedStatement.executeQuery();

            //5. 若结果集不为空，准备一个Map<String, Object>  其中：String是列的别名，Object是列的值
            if (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                //6. 得到结果集的元数据（即ResultSetMetaData对象）
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                //7. 处理ResultSet
                //8. 取出元数据信息
                int columnCount = resultSetMetaData.getColumnCount();//查询结果共有几列
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);//根据下标取出列的别名
                    Object columnValue = resultSet.getObject(columnLabel);//根据列的别名取出对应的值

                    //9. 填充map对象
                    map.put(columnLabel,columnValue);
                }

                //10. 用反射创建Class对应的对象 ---> 核心关键点！
                entity = cl.newInstance();

                //11. 遍历map对象，
                for(Map.Entry<String, Object> entry: map.entrySet()){
                    String fieldName = entry.getKey();
                    Object fieldValue = entry.getValue();

                    //12. 根据[属性名：属性值]构建对象。这里应用了反射的知识
                    //这里的BeanUtils是第三方的Java工具类！
                    BeanUtils.setProperty(entity, fieldName, fieldValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(resultSet,preparedStatement,connection);
        }

        return entity;
    }

    //查询多条记录，返回对应的对象的集合
    public <T> List<T> getForList(Class<T> cl, String sql, Object... args) throws Exception{
        List<T> list = new ArrayList<T>();
        T entity = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = JDBCTools.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //这里取得的结果集可能包含多条记录
            resultSet = preparedStatement.executeQuery();

            //5. 若结果集不为空，准备一个Map<String, Object>  其中：String是列的别名，Object是列的值
            Map<String, Object> map = null;
            while (resultSet.next()) {
                //6. 得到结果集的元数据（即ResultSetMetaData对象）
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                //一个map存放一条记录的各个字段和对应的值
                map = new HashMap<String, Object>();

                //7. 处理ResultSet
                //8. 取出元数据信息
                int columnCount = resultSetMetaData.getColumnCount();//每条记录共有几列
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);//根据下标取出列的别名
                    Object columnValue = resultSet.getObject(columnLabel);//根据列的别名取出对应的值

                    //9. 填充map对象
                    map.put(columnLabel,columnValue);
                }
                //for循环结束后，map内则存放了一条记录的信息，下面就是要把这条信息封装成对象，然后放到list内


                //10. 用反射创建Class对应的对象 ---> 核心关键点！
                entity = cl.newInstance();

                //11. 遍历map对象，对一个对象的属性赋值
                for(Map.Entry<String, Object> entry: map.entrySet()){
                    String fieldName = entry.getKey();
                    Object fieldValue = entry.getValue();

                    //12. 根据[属性名：属性值]构建对象。这里应用了反射的知识
                    //这里的BeanUtils是第三方的Java工具类！
                    BeanUtils.setProperty(entity, fieldName, fieldValue);
                }

                //
                list.add(entity);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(resultSet,preparedStatement,connection);
        }

        return list;
    }

    //返回某条记录中某个字段的值，或是一个统计的值（MIN/MAX/COUNT/SUM等）
    public <E> E getForValue(String sql, Object... args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //建立连接
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            //填充字段
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //执行操作
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return (E) resultSet.getObject(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(resultSet, preparedStatement, connection);
        }

        return null;
    }

}
