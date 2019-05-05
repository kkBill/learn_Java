package reflection;

import inheritance.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * This demo use reflection to print all features of a class
 */

public class ReflectionTest {
    public static void main(String[] args){

        Employee aEmployee = new Employee("kkbill",20000,2019,3,27);
        /**
         */
        Class aClass = aEmployee.getClass();//Object类中的getClass()方法将会返回一个Class类型的实例
        String className = aClass.getName();//Class类中的getName()方法将返回类的名字（包括包名）
        System.out.println(className);

        String name;
        if(args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Please input a class name(e.g. java.util.Date)");
            name = in.next();
        }

        try{
            //print class name and superclass name
            Class cl = Class.forName(name);//返回类名为name的Class对象
            Class supercl = cl.getSuperclass();//返回cl的超类
            String modifiers = Modifier.toString(cl.getModifiers());//
            if(modifiers.length() > 0)
                System.out.println(modifiers + " ");
            System.out.println("class " + name);
            if(supercl != null && supercl != Object.class)//
                System.out.println(" extends " + supercl.getName());

            System.out.print("\n{\n");
            printConstrcutors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.print("\n}");

        }catch (ClassNotFoundException e){//
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * print all constructors of a class
     * @param cl a class
     */
    public static void printConstrcutors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();//
        for(Constructor c : constructors){
            String name = c.getName();//
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());//
            if(modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(name + "(");

            //print param types
            Class[] paramTypes = c.getParameterTypes();//
            for(int j = 0;j < paramTypes.length; j++){
                if(j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());//
            }

            System.out.print(");\n");
        }
    }

    /**
     * print all methods of a class
     * @param cl a class
     */
    public static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();//
        for(Method m : methods){
            Class retType = m.getReturnType();//
            String name = m.getName();//

            System.out.print("  ");
            //print modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");

            //print param types
            Class[] paramTypes = m.getParameterTypes();
            for(int j = 0;j < paramTypes.length; j++){
                if(j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());//
            }

            System.out.print(");\n");
        }
    }

    /**
     * print all fields of a class
     * @param cl a class
     */
    public static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for(Field f : fields){
            Class type = f.getType();//返回域f所属类型的Class对象
            String name = f.getName();
            System.out.print("  ");
            int modifier = f.getModifiers();//
            String modifiers = Modifier.toString(modifier);//将修饰符modifier转换成对应的字符串
            if(modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(type.getName() + " " + name + ";\n");
        }
    }
}
