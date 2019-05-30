package generics;

import java.util.ArrayList;
import java.util.List;

public class SimpleTypeErasure {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("hi");

        List<Integer> list2 = new ArrayList<>();
        list2.add(24);

        Class clazz1 = list1.getClass();
        Class clazz2 = list2.getClass();
        if(clazz1 == clazz2){
            System.out.println("true");
        }
    }
}
