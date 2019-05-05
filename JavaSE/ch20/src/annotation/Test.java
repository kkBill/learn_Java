package annotation;


public class Test {
    //@SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MyClass cl = new MyClass();
        cl.myDeprecatedMethod();
    }
}

class MyClass{
    @Deprecated
    public void myDeprecatedMethod(){
        System.out.println("myDeprecatedMethod()");
    }
}