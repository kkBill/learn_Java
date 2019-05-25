package reusing;

class Base{
    final void foo(){
        System.out.println("Base:foo()");
    }

    private void foo2(){
        System.out.println("Base:foo2()");
    }
}

class Child extends Base{
//    @Override
//    void foo(){
//        System.out.println("Child:foo()");
//    }

//    void foo2(){
//        System.out.println("Child:foo2()");
//    }

    void bar(){
        System.out.println("Child:bar()");
    }
}


public class FinalOverriding {
    public static void main(String[] args) {
        Child child = new Child();
        //child.foo2();//ERROR
        child.bar();
    }
}
