import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InputTest
{

    public static void main(String[] args) throws IOException
    {
        //控制台输入输出
        /*
        Scanner in = new Scanner(System.in);
        System.out.println("What's your name?");
        String name = in.nextLine();//输入一行，可包括空格

        System.out.println("Your school?");
        String school = in.next();//输入一个单词

        System.out.println("Your age?");
        int age = in.nextInt();//输入一个整数

        System.out.println("name: "+name+" school: "+school+" age: "+age);
        */
        //打印时间
        /*
        System.out.println(new Date());
        System.out.printf("%tr", new Date());
        */
        //文件读写
        /*
        Scanner in = new Scanner(Paths.get("test1.txt"));
        String fileString = in.nextLine();
        System.out.println(fileString);

        PrintWriter out = new PrintWriter("test2.txt");
        out.println(23333333);
        out.printf("%s %d\n","Hello, this is file2",399);
        out.close();
        */

        int[] array = new int[10];
        for(int i=0; i<array.length; i++){
            array[i] = i;
        }

        System.out.println(Arrays.toString(array));//打印[1,2,3,...]
        //System.out.println(array[100]);//Out of bound exception

        int[] anotherArray = array;//array 与 anotherArray将引用同一个数组
        anotherArray[0] = 666;
        System.out.println(Arrays.toString(array));

        int[] copyArray = Arrays.copyOf(array, array.length);
        copyArray[0] = 233;
        System.out.println(Arrays.toString(array));

    }
}
