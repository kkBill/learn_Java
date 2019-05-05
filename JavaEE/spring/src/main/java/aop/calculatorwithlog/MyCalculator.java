package aop.calculatorwithlog;

public class MyCalculator implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("method add begins with [" + i + "," +  j + "]");
        int result = i + j;
        System.out.println("method add ends with " + result);

        return result;

    }

    @Override
    public int sub(int i, int j) {
        System.out.println("method sub begins with [" + i + "," +  j + "]");
        int result = i - j;
        System.out.println("method sub ends with " + result);

        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("method mul begins with [" + i + "," +  j + "]");
        int result = i * j;
        System.out.println("method mul ends with " + result);

        return result;
    }

    @Override
    public double div(int i, int j) {
        System.out.println("method div begins with [" + i + "," + j + "]");
        double result = (double) i / (double) j;
        System.out.println("method div ends with " + result);

        return result;
    }
}
