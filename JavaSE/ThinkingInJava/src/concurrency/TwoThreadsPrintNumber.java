package concurrency;

class MyNumber implements Runnable {
    private int number = 1;
    private Object object = new Object();

    @Override
    public void run() {
        for(;;){
            synchronized (this) {
                notify();

                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                       wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }
            }
        }
    }
}

public class TwoThreadsPrintNumber {
    public static void main(String[] args) {
        MyNumber number = new MyNumber();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
