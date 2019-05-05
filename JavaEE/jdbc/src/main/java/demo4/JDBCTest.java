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
     *  正常情况下，通过id查询一个人的信息，只有id匹配，才能查看信息
     *  但是，通过SQL注入，就可以突破这样的限制
     */
    public static void testSQLInjection() throws Exception{

        //正常情况
//        String ID = "9999";
//        String sql = "SELECT * FROM student WHERE ID=" + ID;

        /**
         * 正常情况下，ID = 1234是查不到的！
         * 但是经过SQL非法注入，就可以查询到!!!
         * 这里，由于sql语句是通过拼接形成的，我加入了一个OR语句，使得WHERE条件判断成立，
         * 从而即使ID是不存在的，仍然可以访问数据库
         */
        String ID = "1234";
        String sql = "SELECT * FROM student WHERE ID=" + ID
                      + " OR '" + 1 + "'='" + 1 + "'";//SELECT * FROM student WHERE ID=1234 OR '1'='1'
        System.out.println(sql);
        Connection connection = JDBCTools.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                System.out.println("查询成功！");
                Student student = new Student(resultSet.getString("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Sex"),
                        resultSet.getInt("Age"));
                System.out.println(student);
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
