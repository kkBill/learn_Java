package demo2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest {
    //通过使用自己封装的JDBCTools，重写demo1中的查询操作，就顺畅了好多
    @Test
    public void testResultSet() throws Exception{

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Student student = new Student();

        try{
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            String sql = "select * from student";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.println(resultSet.getString("ID")
                        + "\t" + resultSet.getString("Name")
                        + "\t" + resultSet.getString("Sex")
                        + "\t" + resultSet.getInt("Age"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseResultSetAndStatementAndConnection(resultSet, statement, connection);
        }
    }


    /*************以面向对象的方式插入一条记录****************/
    //从控制台输入学生信息
    private static Student getStudentFromConsole(){
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID:");
        student.setID(scanner.next());

        System.out.print("Name:");
        student.setName(scanner.next());

        System.out.print("Sex:");
        student.setSex(scanner.next());

        System.out.print("Age:");
        student.setAge(scanner.nextInt());

        return student;
    }

    public static void addStudentToTable(Student stu) throws Exception{
        //字符串拼接是非常繁琐的，因此，才会出现更优的工具
        String sql = "INSERT INTO student VALUES('" + stu.getID()
                        + "','" + stu.getName()
                        + "','" + stu.getSex()
                        + "','" + stu.getAge()
                        +"')";

        System.out.println(sql);
        JDBCTools.myUpdate(sql);
    }

    public static void testAddStudentToTable() throws Exception{
        Student student = getStudentFromConsole();
        addStudentToTable(student);
    }

    /*****************以面向对象的方式根据学号查询学生******************/
    //输入一个学生的学号，输出对应的查询结果
    private static String getIDFromConsole(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID:");
        String ID = scanner.next();
        return ID;
    }

    public static Student queryByID(String ID) throws Exception{
        Student student = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            String sql = "SELECT * from student where ID=" + ID;
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                student = new Student(resultSet.getString("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Sex"),
                        resultSet.getInt("Age"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseResultSetAndStatementAndConnection(resultSet, statement, connection);
        }

        return student;
    }

    public static void testQueryByID() throws Exception{
        String id = getIDFromConsole();
        Student student = queryByID(id);
        if(student == null)
            System.out.println("no such a student");
        else
            System.out.println(student);
    }

    /**************************************************/
    public static void main(String[] args) throws Exception{
        //testAddStudentToTable();
        testQueryByID();
    }

}
