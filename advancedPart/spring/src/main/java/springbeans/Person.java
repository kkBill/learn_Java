package springbeans;

import java.util.HashMap;
import java.util.List;

/**
 * 测试通过“属性注入”
 * 属性注入需要通过set方法
 */
class Car{
    private String carBrand;
    private String carPrice;

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carPrice='" + carPrice + '\'' +
                '}';
    }
}

/**
 * 测试通过“构造器注入”
 * 构造器注入需要构造器
 */
class Album{
    private String singer;
    private String song;
    private int rank;

    public Album(String singer, String song, int rank) {
        this.singer = singer;
        this.song = song;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Album{" +
                "singer='" + singer + '\'' +
                ", song='" + song + '\'' +
                ", rank=" + rank +
                '}';
    }
}

public class Person {
    private String name;
    private int age;
    private HashMap<String, Integer> courses;
    private List<Car> cars;


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourses(HashMap<String, Integer> courses) {
        this.courses = courses;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                ", cars=" + cars +
                '}';
    }
}
