package generics;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Base {
    public void f(){
        System.out.println("Base f()...");
    }
}

class Derived extends Base {
    @Override
    public void f(){
        System.out.println("Derived f()...");
    }
}


public class Main {

    /**
     * 无界通配符的使用
     */
    private static void test1(){
        List<Base> baseList = new ArrayList<>();
        baseList.add(new Base());
        List<Derived> derivedList = new ArrayList<>();
        derivedList.add(new Derived());

        /**
         * 虽然 Base 与 Derived 是父子关系，但 List<Base> 与 List<Derived> 不存在父子关系
         */
        //baseList = derivedList; //ERROR
        //show(derivedList); //ERROR

        /**
         * 可以通过通配符 "?" ，List<?> 是任意 List<xx> 类型的父类
         */
        List<?> list = null;
        list = baseList;
        showList(list);
        //list.add(new Base());
        list = derivedList;
        showList(list);

    }

    // 无法通过 List<Base> 来实现泛化，而必须使用 List<?>
//    private void show(List<Base> list){
//
//    }

    private static void showList(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /**
     * 有界通配符的使用
     *
     * <? extends Base> 可以理解为区间 (?, Base]
     * <? super Base> 可以理解为区间 [Base, ?)
     */
    private static void test2(){

        //从继承关系上看，Derived < Base < Object
        List<Derived> derivedList = new ArrayList<>();
        List<Base> baseList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        List<? extends Base> list1 = null;
        List<? super Base> list2 = null;

        list1 = derivedList; // ok
//        list1.add(new Base());// error
//        list1.add(new Derived());// error
        list1 = baseList; // ok
//        list1.add(new Base());// error
//        list1.add(new Derived());// error
        //list1 = objectList; // error

        //list2 = derivedList; // error
        list2 = baseList; // ok
        list2.add(new Base());
        list2.add(new Derived());
        list2 = objectList; // ok
        list2.add(new Base());
        list2.add(new Derived());
    }

    public static void main(String[] args) {
        test1();
    }
}
