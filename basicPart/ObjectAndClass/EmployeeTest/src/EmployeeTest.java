import java.util.*;

public class EmployeeTest {
    public static void main(String[] args){
        Employee[] staff = new Employee[4];//创建Employee类型的数组
        staff[0] = new Employee("Carl",7500, 1994, 10 ,1);
        staff[1] = new Employee("Harry",10000, 1996, 11 ,17);
        staff[2] = new Employee("Tony",9500, 1992, 9 ,13);
        staff[3] = new Employee();//采用默认构造器方法来初始化

        for(Employee e : staff){
            System.out.println(e.getName() + " " + e.getSalary());
        }
        System.out.println();
        Employee member = staff[1];
        member.raiseSalary(10);//工资提高10%
        System.out.println(member.getName() + " " + member.getSalary());
    }
}

class Employee{
    //私有的实例域，即C++中的数据成员
    private String name;
    private double salary;
    private Date hireDay;//Date是Java的一个类类型

    //默认构造器，会对实例域进行默认初始化。数值初始化为0，布尔初始化为false，类初始化为null
    public Employee(){
    }
    //构造器可以多个
    //构造器
    public Employee(String name, double salary, int year, int month, int day){
        this.name = name;//this其实可以不用写，但这么写是为了区分形参和实例域
        this.salary = salary;
        //GregorianCalendar用0表示1月份，故month-1
        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
        this.hireDay = calendar.getTime();
    }

    //以下三个方法为访问器方法，所谓访问器方法，即只读取不修改，一般命名为getXXX()
    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public Date getHireDay(){
        return hireDay;
    }

    //以下方法为更改器方法，一般命名为setXXX()
    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
