package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


class DynamicProxyHandler implements InvocationHandler {
    private Object target;

    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I am dynamic proxy, do something here before call Foo's methods");
        Object object = method.invoke(target, args);
        System.out.println("I am dynamic proxy, do something here after call Foo's methods");
        return object;
    }
}

public class DynamicProxyTest {
    public static void main(String[] args) {
        DynamicProxyHandler handler = new DynamicProxyHandler(new Foo());

        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
                                                        new Class<?>[] {MyInterface.class},  handler);//返回代理类实例
        proxy.foo();
        proxy.bar("zju");
    }
}
