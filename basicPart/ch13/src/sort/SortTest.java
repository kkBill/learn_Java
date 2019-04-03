package sort;

import treeSet.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortTest {
    public static void main(String[] args) {
        ArrayList<Student> stus = new ArrayList<>();
        stus.add(new Student(1003,69));
        stus.add(new Student(1004,91));
        stus.add(new Student(1001,85));
        stus.add(new Student(1002,78));

        System.out.println("原始序列");
        System.out.println(stus);

        //默认根据成绩降序排列(这是在Student类内部实现好的)
        Collections.sort(stus);
        System.out.println("默认排序(根据成绩从高到低)");
        System.out.println(stus);
        //与默认形式相反的排序
        Collections.sort(stus, Collections.reverseOrder());
        System.out.println("与默认相反的排序");
        System.out.println(stus);

        //在类的外部自定义排序规则，即需要自定义Comparator
        //这里利用学号进行排序
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(stus, comp);
        System.out.println("根据学号排序从低到高");
        System.out.println(stus);
        Collections.sort(stus, Collections.reverseOrder(comp));
        System.out.println("根据学号排序从高到底");
        System.out.println(stus);
    }
}
