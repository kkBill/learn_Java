package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void printAllFiles(File file){
        if(file == null || !file.exists()){
            return;
        }

        if(file.isFile()){
            System.out.println(file.getName());
            return;
        }

        if(file.isDirectory()){
            for (File file2 : file.listFiles()) {
                printAllFiles(file2); //递归
            }
        }
    }

    public static void testRandomAccessFile(){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("test.txt","rw");
            String data = "大数据基础平台研发 Java Hadoop Flink";
            data = new String(data.getBytes("UTF-8"),"Unicode");
            raf.writeBytes(data);

            raf.seek(0);//移动文件指针，从0开始偏移6个字节
            System.out.println(raf.readUTF());

//            byte[] buffer = new byte[2];
//            int len = 0;
//            while((len = raf.read(buffer)) != -1){
//                System.out.print(new String(buffer, 0, len));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf != null){
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        printAllFiles(new File("D:\\temp"));
//        File file = new File("D:\\temp\\test");
//        System.out.println(file.mkdir());
        testRandomAccessFile();
    }
}
