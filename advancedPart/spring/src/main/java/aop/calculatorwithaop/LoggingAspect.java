package aop.calculatorwithaop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *声明为切面
 * 1. 添加@Component，让IoC容器管理
 * 2. 添加@Aspect，声明为切面
 */

@Component
@Aspect
public class LoggingAspect {

    /**
     * 定义一个方法，用于声明切点表达式。一般的，该方法中不再需要添加其他的代码
     * 使用时，把该方法的函数名替换为对应的value值即可，如下面的@Before的声明
     */
    @Pointcut("execution(* *(int, int))")
    public void declareJoinPointExpression(){}


    /**
     * 这里注解的参数是什么意思？
     * 前置通知：在目标方法开始之前执行
     */
    @Before("declareJoinPointExpression()")  //把后面的注解也都改成这样即可
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("the method " + methodName + " begins with " + args);
    }

    /**
     * 后置通知：在目标方法开始之后执行（不管是否发生异常）
     * 在后置通知中无法获取目标方法的返回值，而需要在返回通知中获取
     * 因为方法可能会出现异常，导致不能正常结束，因此，返回结果不在后置通知中获取
     */
    @After("execution(* *(int, int))")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("the method " + methodName + " ends");
    }

    /**
     * 返回值通知：当目标方法正常执行后，返回对应的执行结果
     * 注意是-->“正常执行”后才会有这个通知
     */
    @AfterReturning(value = "execution(* *(int, int))",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("the method " + methodName + " ends with " + result);
    }

    /**
     * 异常通知：在目标方法出现异常时，会执行的代码
     * 可以访问到异常对象，且可以执行需要捕获的异常的种类，比如，
     * 下面的例子中，也可以指定异常为NullPointerException，也就是捕获空指针异常，但是由于这里是发生的是数学异常，则不会捕获
     */

    @AfterThrowing(value = "execution(* *(int, int))",
                   throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("the method " + methodName + "occurs exception " + e);
    }


//    /**
//     * 环绕通知：是功能最全的通知，结合了前面的四种通知，有点类似于动态代理的过程
//     * 环绕通知需要携带 ProceedingJoinPoint 参数， 该参数可以决定是否执行目标方法
//     * 且环绕通知必须有返回值，该返回值就是目标方法的返回值
//     */
//    @Around("execution(* *(int, int))")
//    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint){
//        Object result = null;
//        String methodName = proceedingJoinPoint.getSignature().getName();
//        Object[] args = proceedingJoinPoint.getArgs();
//
//        try{
//            //前置通知
//            System.out.println("the method " + methodName + " begins with " + Arrays.asList(args));
//            result = proceedingJoinPoint.proceed();
//            //返回值通知
//            System.out.println("the method " + methodName + " ends with " + result);
//        }catch (Throwable e){
//            //异常通知
//            System.out.println("the method " + methodName + "occurs exception " + e);
//        }
//        //后置通知
//        System.out.println("the method " + methodName + " ends");
//
//        //必须有返回值
//        return result;
//    }

}
