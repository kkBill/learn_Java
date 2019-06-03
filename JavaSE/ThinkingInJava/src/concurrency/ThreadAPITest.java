package concurrency;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(this.getName() + this.getPriority() + ":++" + i);
            }

//            if(i % 20 == 0){
//                this.yield();//释放当前线程对cpu的占用权
//            }
        }
    }

    //通过构造器来自定义线程的名字
    public MyThread(String name) {
        super(name);
    }
}

public class ThreadAPITest {
    public static void main(String[] args) {

        MyThread t = new MyThread("lalala：");
        //也可以通过set方法来命名线程
        //t.setName("hehehe");
        t.start();

        Thread.currentThread().setName("主线程：");
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":**" + i);
                if(i == 20){
                    try {
                        t.join(); //等线程t执行结束后再执行主线程，此时主线程进入阻塞状态
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
