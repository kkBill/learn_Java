package abstractClass;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends Person {//Employee类继承自Person类
    //private String name;//name域就不需要再次声明了，因为已经从基类中继承
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary, int year, int month, int day){
        super(name);//调用基类的构造器
        this.salary = salary;
        GregorianCalendar calendar = new GregorianCalendar(year, month-1,day);
        this.hireDay = calendar.getTime();
    }

    /*getName()方法不需要重新定义，因为已经从基类中继承过来了
    public String getName() {
        return name;
    }
    */
    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = byPercent * salary / 100;
        salary += raise;
    }

    @Override//override即对抽象方法的覆盖
    public String getDescription() {
        return String.format("an employee with a salary of $%.2f", salary);
    }
}
