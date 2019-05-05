package proxy.simpleProxy;

interface Subject {
    void doSomething();
}

class RealObject implements Subject {
    public void doSomething() {
        System.out.println("I am RealObject ...");
    }
}

class SimpleProxy implements Subject {
    private Subject target;

    public SimpleProxy(Subject target) {
        this.target = target;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy start ...");
        target.doSomething();//执行目标对象的方法
        System.out.println("SimpleProxy end ...");
    }
}

public class SimpleProxyDemo {
    public static void main(String[] args) {
        //目标对象(or 委托对象)
        RealObject realObj = new RealObject();
        //代理对象，把目标对象传给代理对象，建立代理关系
        SimpleProxy proxyObj = new SimpleProxy(realObj);
        //代理对象开始执行请求
        proxyObj.doSomething();
    }
}
