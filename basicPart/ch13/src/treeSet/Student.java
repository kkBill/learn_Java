package treeSet;

import java.util.Objects;

// 自定义一个类，为了类在容器中能按照指定规则自动排序（比如存储在TreeSet中）
// 则必须实现Compareable接口的compareTo()方法
// 在Student类中，我们按照成绩进行排序

public class Student implements Comparable<Student> {
    private int id;
    private int score;

    public Student(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", score=" + score + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (this.getClass() != obj.getClass()) return false;
        Student stu = (Student) obj;
        return id == stu.id;//比较两个学生是否相同比较学号就好了
    }

    //覆盖了equals()方法，就要重新定义hashCode()方法
    public int hashCode() {
        return Objects.hash(id, score);
    }

    //Comparable接口的唯一一个方法；换言之，实现Compareable接口必须要实现compareTo()方法
    public int compareTo(Student other) {
        return Integer.compare(score, other.score);
    }
}
