package rtti;

import java.util.Arrays;

abstract class Shape{
    abstract void draw();
}

interface ShapeInterfaces{
    double area();
}

interface NoUseInterface{

}

class Circle extends Shape implements ShapeInterfaces, NoUseInterface {

    // private field
    private int radius;

    // public field
    public int a;
    public double b;

    // private method
    private void foo(){}

    Circle(){ }

    Circle(int radius){
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("this is Circle...");
    }

    public double area(){
        return 3.14 * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

public class ClassTest {

    public static void main(String[] args) throws Exception{
        //Circle circle = new Circle(1);

        // 通过Class.forName("包名.类名") 就可获得对应的Class对象, Class对象包含了Circle类的所有信息
        Class clazz = Class.forName("rtti.Circle");
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getSuperclass());
        System.out.println(Arrays.asList(clazz.getInterfaces()));//Circle类实现了哪些接口
        System.out.println(clazz.getMethods());
        //System.out.println(Arrays.asList(clazz.getFields()));//Circle类中声明的public field
        //System.out.println(Arrays.asList(clazz.getMethods()));//Circle类中声明的public method
        //System.out.println(clazz.getMethod("foo")); //ERROR, foo() is a private method

        System.out.println(int.class);

        Circle circle = (Circle) clazz.newInstance();//还可以通过Class对象创建具体的实例，但是必须保证被创建的对象必须有默认构造函数
        System.out.println(circle);

        //除了利用forName()创建Class对象，还可以利用.class来创建Class对象
        Class clazz2 = Circle.class;
        System.out.println(clazz2.getName());

    }
}
