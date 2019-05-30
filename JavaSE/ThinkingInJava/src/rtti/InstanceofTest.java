package rtti;

class Simple1 {

}

class Animal {

}

class Dog extends Animal {

}

public class InstanceofTest {
    public static void main(String[] args) {
        Simple1 s = new Simple1();
        System.out.println(s instanceof Simple1);

        Dog dog = new Dog();
        // 两者等价，判断dog是否是Animal的实例（或子类的实例）
        System.out.println(dog instanceof Animal);
        System.out.println(Animal.class.isInstance(dog));
        System.out.println(Dog.class == dog.getClass());
        System.out.println(Dog.class == new Animal().getClass());

        //Dog dog1 = (Dog) new Animal();
    }
}
