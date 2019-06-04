package concurrency;

import java.io.ObjectInputStream;
import java.util.concurrent.locks.ReentrantLock;

class Window3 implements Runnable {
    private int ticket = 100;
    // 不同的线程必须同用一个lock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {

            lock.lock(); // 加锁
            try {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " ticket NO: " + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window3 w = new Window3();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

        new Object();
    }
}
