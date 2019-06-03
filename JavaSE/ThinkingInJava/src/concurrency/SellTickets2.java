package concurrency;

class Window2 implements Runnable {
    /**
     * 通过实现 Runnable 接口，数据域是多线程共享的，因为只创建了一个 window 类对象
     */
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            doSell();
        }
    }

    // 同步方法
    private synchronized void doSell(){ // 这里的同步监视器是默认的 this
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + " ticket NO: " + ticket);
            ticket--;
        }
    }
}

public class SellTickets2 {
    public static void main(String[] args) {
        Window2 w = new Window2();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        //设置线程名字
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}