package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharStreamTest {

    private static void copyTextFile(String from, String to) {
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(from);
            writer = new FileWriter(to);

            char[] cbuf = new char[1024];
            int len;
            // 把from文件数据读到buf中，再写入to文件中
            while ((len = reader.read(cbuf)) != -1) {
                System.out.println(new String(cbuf, 0, len));
                writer.write(cbuf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
//        char ch = '中';
//        System.out.println(ch);
//
//        String str1 = "知";
//        byte[] bytes = str1.getBytes(StandardCharsets.UTF_16);
//
//        String str2 = new String(bytes, StandardCharsets.UTF_16);
//        System.out.println(str2);
//
////        String str3 = "abc";
////        String str4 = "计算机";
////        System.out.println(str3.length() + ", " + str4.length());
//        char[] str3 = {'a', 'b', 'c'};
//        char[] str4 = {'计', '算', '机'};
//        System.out.println(str3.length + ", " + str4.length);
        copyTextFile("test.txt","test-copy.txt");

        //System.out.println(Charset.defaultCharset());
    }
}
