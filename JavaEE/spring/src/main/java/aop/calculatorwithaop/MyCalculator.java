package aop.calculatorwithaop;

import org.springframework.stereotype.Component;

@Component
public class MyCalculator implements Calculator {
    public int add(int i, int j) {
        int result = i + j;
        return result;

    }

    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

    public int mul(int i, int j) {
        int result = i * j;
        return result;
    }

    public double div(int i, int j) {
        double result = i / j;
        return result;
    }
}
