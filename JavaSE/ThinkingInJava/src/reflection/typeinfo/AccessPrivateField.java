package reflection.typeinfo;

import java.lang.reflect.Field;

class MyClass {
    private int i = 233;
    private final String s1 = "I am totally safe";
    private String s2 = "I am not safe";

    @Override
    public String toString() {
        return "MyClass{" +
                "i=" + i +
                ", s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}

public class AccessPrivateField {
    public static void main(String[] args) throws Exception {
        MyClass a = new MyClass();
        //a.s1; //ERROR 不能访问私有域

        /**
         * 通过反射获取[private域]，甚至改变[非final的private域]
         */
        Field field = a.getClass().getDeclaredField("i");
        field.setAccessible(true);
        System.out.println(field.get(a));
        System.out.println(a.toString());
        field.set(a, 9999);
        System.out.println(a.toString() + '\n');

        field = a.getClass().getDeclaredField("s1");
        field.setAccessible(true);
        System.out.println(field.get(a));
        System.out.println(a.toString());
        field.set(a, "xxxxxx");
        System.out.println(a.toString() + '\n');

        field = a.getClass().getDeclaredField("s2");
        field.setAccessible(true);
        System.out.println(field.get(a));
        System.out.println(a.toString());
        field.set(a, "xxxxxx");
        System.out.println(a.toString());

    }
}
