package method;

import java.lang.reflect.Method;

public class MethodTest {
    public static void main(String[] args) throws Exception {

        System.out.println("---常规调用---");
        Foo foo = new Foo();
        System.out.println(Foo.add(1,2));
        System.out.println(foo.multiply(3,4));
        foo.show("this is show_a");
        foo.show();

        System.out.println("---通过反射机制调用---");
        Class cl = foo.getClass();//获取存储Foo类运行时类型信息的Class对象
        //通过getMethod()指定方法名和参数列表，来获取foo对象内的指定方法
        Method m1 = cl.getMethod("add", int.class, int.class);
        Object obj1 = m1.invoke(null,1,2);//因为m1调用的方法是静态方法，所以第一个参数为null
        System.out.println(obj1);

        Method m2 = cl.getMethod("multiply", int.class, int.class);
        Object obj2 = m2.invoke(foo,3,4);//因为m2调用的方法不是静态方法，所以第一个参数要传入
        System.out.println(obj2);

        Method m3 = cl.getMethod("show",String.class);
        m3.invoke(foo,"hahahaha");

        Method m4 = cl.getMethod("show");
        m4.invoke(foo);
    }
}

class Foo {
    public static int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public void show(String str) {
        System.out.println(str);
    }

    public void show() {
        System.out.println("this is show_b");
    }
}