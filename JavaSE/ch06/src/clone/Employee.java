package clone;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date();
    }

    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();//必须把对可变对象的引用重新克隆!
        return cloned;
    }

    //改变hireDay域
    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    //改变salary域
    public void raiseSalary(double byPercent) {
        double raise = byPercent * salary / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[name: " + name + ", salary: " + salary + ", hireDay: " + hireDay + "]";
    }
}
