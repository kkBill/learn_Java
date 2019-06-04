package concurrency;

class SimpleThread extends Thread {

    /**
     * 重写run()方法，在run()内书写该线程要做的事情
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":++" + i);
            }
        }
    }
}


//class SimpleThread1 implements Runnable {
//    @Override
//    public void run() {
//        for (int i = 0; i < 100; i++) {
//            if (i % 2 == 0) {
//                System.out.println(Thread.currentThread().getName() + ":--" + i);
//            }
//        }
//    }
//}

public class ThreadTest {
    public static void main(String[] args) {
        SimpleThread thread0 = new SimpleThread();
        System.out.println(thread0.getState());//状态为NEW
        thread0.setName("线程1");
        thread0.start();
        System.out.println(thread0.getState());//状态为RUNNABLE

        SimpleThread thread2 = new SimpleThread();
        thread2.setName("线程2");
        thread2.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":++" + i);
                    }
                }
            }
        }.start();

//        SimpleThread1 thread1 = new SimpleThread1();
//        new Thread(thread1).start();




        // 在main线程中执行
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":**" + i);
            }
        }
    }
}
