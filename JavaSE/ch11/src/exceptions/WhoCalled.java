package exceptions;

/**
 * 该例子演示了栈轨迹(Stack Trace)的信息
 * getStackTrace()方法声明如下：
 * public StackTraceElement[] getStackTrace()
 * 其中每一个StackTraceElement代表了一层调用栈的信息，本示例中仅以打印方法名为例
 */

public class WhoCalled {
    static void f(){
        //code here
        try{
            throw new Exception();
        }catch (Exception e){
            //打印栈轨迹的信息
            for(StackTraceElement ste : e.getStackTrace()){
                System.out.println(ste.getMethodName());
            }
        }
    }
    static void g(){ f(); }
    static void h(){ g(); }

    public static void main(String[] args) {
        f();
        System.out.println("-------------------");
        g();
        System.out.println("-------------------");
        h();
    }
}
