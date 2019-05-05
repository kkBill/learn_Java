package aop.calculatorwithproxy;

public class Main {

    public static void main(String[] args) {
        Calculator target = new MyCalculator();
        Calculator proxy = new CalculatorProxy(target).getCalculatorProxy();

        System.out.println("-->"+proxy.add(1,2));
        System.out.println("-->"+proxy.sub(4,2));
        System.out.println("-->"+proxy.mul(3,2));
        System.out.println("-->"+proxy.div(7,2));

    }

}
