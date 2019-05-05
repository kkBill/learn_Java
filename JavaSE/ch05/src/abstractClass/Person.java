package abstractClass;

public abstract class Person {//利用关键字abstract来定义抽象类
    public abstract String getDescription();//利用abstract关键字来声明抽象方法
    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
