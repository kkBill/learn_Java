package treeSet;

import java.util.Comparator;
import java.util.TreeSet;

//本示例展示了自定义排序的两种方法
//一种是写在类的内部（即实现Compareable接口）
//另一种是写在类的外部（即实现Comparatar接口）
public class TreeTest {
    public static void main(String[] args) {
        //方法1
        //创建TreeSet，集合内存储Student对象
        //因为在Student内已经自定义了比较方法，因此默认是按照成绩进行排序
        TreeSet<Student> parts = new TreeSet<>();
        parts.add(new Student(1001, 97));
        parts.add(new Student(1002, 95));
        parts.add(new Student(1003, 90));
        System.out.println(parts);//根据成绩从低到高输出

        //方法2
        //自定义一个内部类，作为比较器，按照id排序
        //需要实现Comparator接口，该接口含有一个compare()方法
        //注意区分，Compareable接口内的方法是compareTo()方法；
        class StudentComparator implements Comparator<Student> {
            @Override
            public int compare(Student a, Student b) {
                int idA = a.getId();
                int idB = b.getId();
                return idA - idB;
            }
        }
        StudentComparator comp = new StudentComparator();
        TreeSet<Student> sortById = new TreeSet<>(comp);//将比较器对象comp传递给TreeSet的构造器

        //但一般都写成匿名类的形式，如下
        TreeSet<Student> sortById2 = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                int idA = a.getId();
                int idB = b.getId();
                return idA - idB;
            }
        });


        sortById.addAll(parts);
        System.out.println(sortById);//根据id从小到大输出
        sortById2.addAll(parts);
        System.out.println(sortById2);
    }
}
