package innerclasses;

interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {
    private int i = 0;

    public void increment() {
        i++;
        System.out.println("Callee1: " + i);
    }
}

class MyIncrement {
    public void increment() {
        System.out.println("other operation");
    }

    static void func(MyIncrement mi) {
        mi.increment();
    }
}

class Callee2 extends MyIncrement {
    private int i = 0;

    public void increment() {
        super.increment();
        i++;
        System.out.println("Callee2: " + i);
    }

    //inner class
    private class Closure implements Incrementable {
        public void increment() {
            Callee2.this.increment();
        }
    }

    //返回类型为接口类型
    //
    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;
    //接口变量必须引用实现了接口的类对象！
    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();//采用默认构造器
        Callee2 c2 = new Callee2();
        MyIncrement.func(c2);//func()为静态方法
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
