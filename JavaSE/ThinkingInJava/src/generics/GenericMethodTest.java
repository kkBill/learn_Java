package generics;

import java.util.ArrayList;
import java.util.List;

class Apple {
    private int id;
    Apple(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple[" + id + "]";
    }
}

//泛型类
class Staff<T> {
    public boolean add(T t){
        //...
        return true;
    }

//    public static void f(T t){
//
//    }
}

public class GenericMethodTest {
    //可以打印任意类型的一个泛型方法
    private static <K, V> void GenericPrint(K k, V v) {
        System.out.println(k + ", " + v);
    }

    //把泛型与可变参数结合，生成一个存储任意类型的List
    private static <T> List<T> makeList(T... args){
        List<T> list = new ArrayList<>();
        for (T item : args){
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        GenericPrint(3.14, "hi");
        GenericPrint(new Apple(1), 233);

        List<String> list = makeList("Kobe Bryant","Keven Durant","Stephen Curry");
        System.out.println(list);
        List<Apple> appleList = makeList(new Apple(2), new Apple(3));
        System.out.println(appleList);

        Staff<String> staff = new Staff<>();
    }
}
