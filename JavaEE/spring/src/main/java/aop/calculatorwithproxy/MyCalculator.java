package aop.calculatorwithproxy;

public class MyCalculator implements Calculator {
    @Override
    public int add(int i, int j) {
        int result = i + j;
        return result;

    }

    @Override
    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;
        return result;
    }

    @Override
    public double div(int i, int j) {
        double result = (double) i / (double) j;
        return result;
    }
}
