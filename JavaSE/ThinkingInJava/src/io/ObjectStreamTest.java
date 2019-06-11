package io;

import java.io.*;

class Person implements Serializable{
    private String name;
    private int age;
    private double score;
    private boolean isMale;

    private static final long serialVersionUID = 12313215466515L;

    Person(String name, int age, double score, boolean isMale) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", isMale=" + isMale +
                '}';
    }
}

public class ObjectStreamTest {
    public static void main(String[] args) throws Exception{
        Person p = new Person("kkBill",24,95.5,true);

        String filename = "person.dat";
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
        oout.writeObject(p);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filename));
        Person p2 = (Person)oin.readObject();
        oin.close();

        System.out.println(p2);
    }
}
