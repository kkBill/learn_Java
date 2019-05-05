package com.linkedList;

import java.util.*;

/**
 * LinkedList是链表，在Java中，所有链表实际上都是双向的
 * ArrayList是动态数组，是非线程安全的
 * Vector也是动态数组，但其所有方法都是同步的，是线程安全的
 * 对于ArrayList和Vector的选用，应该视情况而定
 */
public class LinkedListTest {

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Kobe");
        arr.add("James");
        arr.add("Curry");
        System.out.println("arr[1]: " + arr.get(1));//数组列表可以随机存取,O(1)
        arr.add(0, "D-Wade");//在下标为0处插入,O(N)

        List<String> list = new LinkedList<>(arr);
        ListIterator<String> iter = list.listIterator();
        while (iter.hasNext()) {//通过迭代器访问
            System.out.println(iter.next());
        }
        System.out.println();
        if(iter.hasNext())
            System.out.println(iter.next());
        else {
            System.out.println("sorry, at the end of List");
            if(iter.hasPrevious()){
                System.out.println("now! back forward");
                while(iter.hasPrevious()){
                    System.out.println(iter.previous());
                }
            }
        }

        /**
         * 通过迭代器进行删除操作，remove()方法。
         * 这个方法必须紧跟在访问一个元素之后执行
         * 这对于所有的集合都是一致的
         */
        iter.next();
        iter.remove();
        System.out.println("after delete first element");
        System.out.println(list);

        /**
         * 通过List特有的删除方法，注意与上面的区别
         */
        list.remove(1);//删除第1个元素
        System.out.println("after delete element of index 1");
        System.out.println(list);
    }
}
