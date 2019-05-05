package aop.calculatorwithaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1. 创建Spring的IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-applicationContext.xml");

        //2. 从IoC容器中获取bean实例
        Calculator calculator = (Calculator) ctx.getBean("myCalculator");

        //3. 使用bean
        int result = calculator.add(2,3);
        System.out.println("result: "+result);

        double d = calculator.div(5,3);
        System.out.println("result: "+d);
    }
}
