package demo2;

public class Student {
    private String ID;
    private String Name;
    private String Sex;
    private int Age;

    public Student() {
    }

    public Student(String ID, String name, String sex, int age) {
        this.ID = ID;
        Name = name;
        Sex = sex;
        Age = age;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public int getAge() {
        return Age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                '}';
    }
}
