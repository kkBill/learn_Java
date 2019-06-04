package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1. 实现一个Callable接口的实现类
 * 2. 重写call()方法，将此线程要做的事情写在call()方法中，支持返回值
 * 3.
 */

class CalculateNumber implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;

        for (int i = 1; i <= 100 ; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        CalculateNumber calculateNumber = new CalculateNumber();
        FutureTask task = new FutureTask(calculateNumber);

        Thread t1 = new Thread(task);
        t1.start();

        try {
            // 获取线程的返回值
            Object sum = task.get();
            System.out.println("结果：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
