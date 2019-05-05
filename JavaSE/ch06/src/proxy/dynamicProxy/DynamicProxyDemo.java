package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Subject {
    //在接口中声明两个方法，一个有参无返回值，一个无参有返回值
    void sing(String str);
    int dance();

}

//委托类
class RealObject implements Subject {
    @Override
    public void sing(String str) {
        System.out.println("function sing "+str);
    }

    @Override
    public int dance() {
        System.out.println("function dance ");
        return 0;
    }
}

//代理类
class MyInvocationHandler implements InvocationHandler {
    //这是我们要代理的真实对象
    private Object target;
    //构造方法，把委托对象赋值给真实对象
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public void doOtherthing1(){
        System.out.println("we can expand function before");
    }

    public void doOtherthing2(){
        System.out.println("we can expand function after\n");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //可以在调用委托对象的方法之前加入扩展的方法
        doOtherthing1();

        //调用委托对象的方法
        //如何理解Method对象的invoke()方法？暂时还理解不了！
        //当代理对象调用委托对象的方法时，
        //会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(target, args);//

        //可以在调用委托对象的方法之后加入扩展的方法
        doOtherthing2();

        return result;
    }
}

public class DynamicProxyDemo {
    public static void main(String[] args) {
        //1.创建委托对象
        RealObject realObj = new RealObject();
        //2.创建调用处理器对象
        MyInvocationHandler handler = new MyInvocationHandler(realObj);
        //3.动态生成代理对象
        //理解不了！！！
        Subject proxyObj = (Subject) Proxy.newProxyInstance(RealObject.class.getClassLoader(),
                RealObject.class.getInterfaces(), handler);
        //4.通过代理对象调用方法
        //为什么没有调用invoke()方法？关于invoke()方法到底是怎样的机制？
        proxyObj.sing("hello");
        int ret = proxyObj.dance();
        System.out.println(ret);
    }
}
