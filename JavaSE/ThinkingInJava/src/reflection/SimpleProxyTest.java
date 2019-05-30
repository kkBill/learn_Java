package reflection;

interface MyInterface {
    void foo();
    void bar(String s);
}

class Foo implements MyInterface {
    @Override
    public void foo() {
        System.out.println("Foo foo()...");
    }

    @Override
    public void bar(String s) {
        System.out.println("Foo bar(): " + s);
    }
}

class SimpleProxy implements MyInterface {
    private MyInterface target;
    public SimpleProxy(MyInterface target){
        this.target = target;
    }

    @Override
    public void foo() {
        System.out.println("I am SimpleProxy, Before call Foo.foo(), I can do something here...");
        target.foo();
    }

    @Override
    public void bar(String s) {
        System.out.println("I am SimpleProxy, Before call Foo.bar(), I can do something here...");
        target.bar(s);
    }
}


public class SimpleProxyTest {
    public static void main(String[] args) {
        SimpleProxy proxy = new SimpleProxy(new Foo());
        proxy.foo();
        proxy.bar("haha");
    }
}
