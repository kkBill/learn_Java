package generics;

/**
 * 泛型最基本的用法，定义一个泛型类，可以存储任意的类型参数
 * @param <T>
 */
class Holder<T> {
    private T a;
    public Holder(){}
    public Holder(T a){
        this.a = a;
    }

    public void set(T a){
        this.a = a;
    }

    public T get(){
        return a;
    }

}

//继承时可以提前明确类型参数，这样对于 SubHolder 而言就不是泛型了
class SubHolder extends Holder<String> {
}

//继承时也可以不指定类型参数，这样对于 SubHolder 而言依然是泛型
class SubHolder2<T> extends Holder<T> {
}

class Car{
    public void foo(){
        System.out.println("Car foo()...");
    }
}

public class HolderTest {
    public static void main(String[] args) {
        //可以往holder中存放int
        Holder<Integer> intHolder = new Holder<>(24);
        System.out.println(intHolder.get());

        //可以往holder中存放String
        Holder<String> strHolder = new Holder<>("Hello ZJU");
        System.out.println(strHolder.get());
        strHolder.set("Hello World");
        System.out.println(strHolder.get());

        //还可以往holder中存放类
        Holder<Car> carHolder = new Holder<>(new Car());
        carHolder.get().foo();
    }
}
