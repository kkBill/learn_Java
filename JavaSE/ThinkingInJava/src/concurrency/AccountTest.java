package concurrency;

/**
 * 甲乙两人同用一个账户，两人分别向账户转账3000元，每次存入1000元
 */
class Account implements Runnable {
    private double balance = 1000;

    private void withdraw(double amount) {
        if (balance > 0) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取钱成功，当前账户余额为：" + balance);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            withdraw(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        Thread c1 = new Thread(account);
        Thread c2 = new Thread(account);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
