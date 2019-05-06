package demo4;

import demo3.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest {

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

    //通过改进工具类中的update方法后，就更加通用了！

    public static void addStudentToTable(Student stu) throws Exception{
        String sql = "INSERT INTO student VALUES(?,?,?,?)";
        JDBCTools.myUpdate(sql, stu.getID(), stu.getName(), stu.getSex(), stu.getAge());
    }

    public static void testAddStudentToTable() throws Exception{
        Student student = getStudentFromConsole();
        addStudentToTable(student);
    }


    /*************演示SQL注入********/
    /**
     *  正常情况下，只有用户名和密码匹配才能成功
     *  但是，通过SQL注入，就可以突破这样的限制
     */
    public static void testSQLInjection() throws Exception{
        //正常情况，模拟正确输入的用户名和密码
//        String username = "Tom";
//        String password = "1234";
//        String sql = "SELECT * FROM user WHERE username = '" + username
//                    + "' AND "
//                    + " password = '" + password + "' ";

        //sql注入，恶意输入用户名和密码
        String username = "a' OR password = ";
        String password = " OR '1'='1";

        String sql = "SELECT * FROM user WHERE username = '" + username
                + "' AND "
                + " password = '" + password + "' ";

        //上述sql语句等价于：SELECT * FROM user WHERE username = 'a' OR password = ' AND  password = ' OR '1'='1'

        System.out.println(sql);
        Connection connection = JDBCTools.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                System.out.println("查询成功！");
                System.out.println(resultSet.getString("username")
                        + " " + resultSet.getString("password"));
            }
            else{
                System.out.println("查询失败！");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseDB(resultSet, statement, connection);
        }
    }

    /**************************************************/
    public static void main(String[] args) throws Exception{
        //testAddStudentToTable();
        testSQLInjection();
    }

}
