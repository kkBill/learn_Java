package interfaces;

/**
 * Monkey类通过implements关键字继承Animal接口
 */

public class Monkey implements Animal{
    @Override
    public void eat() {
        System.out.println("monkey is eating!");
    }

    @Override
    public void travel() {
        System.out.println("monkey is traveling!");
    }

    public void appearance() {
        System.out.println("monkey looks like human being.");
    }

    public static void main(String[] args) {
        Monkey m = new Monkey();
        m.eat();
        m.travel();
        m.appearance();
    }
}
