package concurrency;

class Window implements Runnable {
    /**
     * 通过实现 Runnable 接口，数据域是多线程共享的，因为只创建了一个 window 类对象
     */
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            // 由 synchronized 关键字来同步代码块
            // this 在实际运行时就是 Window 的对象，由于只创建了一个Window对象，
            // 因此保证了多个线程都是操作的同一把锁
            //synchronized (Window.class) { // 这种方法也可以
            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " ticket NO: " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class SellTickets {
    public static void main(String[] args) {
        Window w = new Window();
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
