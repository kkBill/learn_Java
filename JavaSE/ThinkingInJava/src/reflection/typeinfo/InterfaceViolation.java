package reflection.typeinfo;

class Foo implements MyInterface {
    public void f(){
        System.out.println("Foo f()...");
    }
    public void g(){
        System.out.println("Foo g()...");
    }
}





public class InterfaceViolation {
    public static void main(String[] args) {
        MyInterface a = new Foo();
        a.f();
        //a.g(); //compile error 注意，这和继承是不同的
        System.out.println(a.getClass().getName());

        if(a instanceof Foo){
            Foo foo = (Foo) a; //转型
            foo.g();
        }
    }
}
