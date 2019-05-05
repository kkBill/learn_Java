package demo3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCTest {

    /*************以面向对象的方式插入一条记录****************/
    /*************通过PreparedStatement简化sql语句的处理********/
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
        //字符串拼接是非常繁琐的，且容易出错
//        String sql = "INSERT INTO student VALUES('" + stu.getID()
//                        + "','" + stu.getName()
//                        + "','" + stu.getSex()
//                        + "','" + stu.getAge()
//                        +"')";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO student VALUES(?,?,?,?)";

            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,stu.getID());
            preparedStatement.setString(2,stu.getName());
            preparedStatement.setString(3,stu.getSex());
            preparedStatement.setInt(4,stu.getAge());

            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseStatementAndConnection(preparedStatement,connection);
        }
    }

    public static void testAddStudentToTable() throws Exception{
        Student student = getStudentFromConsole();
        addStudentToTable(student);
    }


    /**************************************************/
    public static void main(String[] args) throws Exception{
        testAddStudentToTable();
    }

}
