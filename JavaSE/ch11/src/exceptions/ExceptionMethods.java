package exceptions;

/**
 * 该例子演示了如何使用Exception类型的方法
 */
public class ExceptionMethods {
    public static void main(String[] args) {
        try{
            throw new Exception("My Exception");
        }catch (Exception e){
            System.out.println("catch Exception");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("toString(): " + e.toString());
            System.out.println("printStackTrace(): ");
            e.printStackTrace(System.out);
        }
    }
}
