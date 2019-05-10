package com.kkbill.springboot01yamlconfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

//@Validated
@Component
//@PropertySource(value = {"classpath:car.properties","classpath:courses.properties"})
@ConfigurationProperties(prefix = "person")

public class Person {
    /**
     * 进行数据校验
     */

//    @Email
//    @Value("thisisnotaemail")
//    private String email;

//    @Value("${person.name}") //从配置文件中获得
    private String name;
//    @Value("#{2*11}") //SpEL表达式
    private int age;
//    @Value("false") //直接赋值
    private boolean isStudent;
    private Date birthday;

    private Car car;
    private List<String> courses;
    private Map<String,Integer> scores;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", birthday=" + birthday +
                ", car=" + car +
                ", courses=" + courses +
                ", scores=" + scores +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }
}
