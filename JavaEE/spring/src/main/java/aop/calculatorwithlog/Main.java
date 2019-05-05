package aop.calculatorwithlog;

public class Main {

    public static void main(String[] args) {
        MyCalculator calculator = new MyCalculator();

        System.out.println("-->"+calculator.add(1,2));
        System.out.println("-->"+calculator.sub(4,2));
        System.out.println("-->"+calculator.mul(3,2));
        System.out.println("-->"+calculator.div(7,2));

    }

}
