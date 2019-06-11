package io;

import java.io.*;

public class ByteStreamTest {
    public static void copyNontextFile(String from, String to){
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in =  new FileInputStream(from);
            out = new FileOutputStream(to);

            byte[] buffer = new byte[512];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyNontextFileWithBuffer(String from, String to){
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;

        try {
            bin = new BufferedInputStream(new FileInputStream(from));
            bout = new BufferedOutputStream(new FileOutputStream(to));

            byte[] buffer = new byte[512];
            int len = 0;
            while ((len = bin.read(buffer)) != -1){
                bout.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bout != null){
                    bout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(bin != null){
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void dataStreamWrite() throws IOException{
        DataOutputStream dout = new DataOutputStream(new FileOutputStream("xxx.txt"));
        dout.writeUTF("xiao ming");
        dout.writeInt(24);
        dout.writeDouble(94.5);
        dout.flush();

        dout.close();
    }

    // 写入文件后直接打开是会乱码的，必须由 DataInputStream 读取
    private static void dataStreamRead() throws IOException{
        DataInputStream din = new DataInputStream(new FileInputStream("xxx.txt"));

        System.out.println(din.readUTF());
        System.out.println(din.readInt());
        System.out.println(din.readDouble());

        din.close();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        copyNontextFile("D:\\download\\牛客网项目\\视频\\高级-03.mp4","D:\\download\\牛客网项目\\视频\\copy.mp4");//total time:6781
        //copyNontextFileWithBuffer("D:\\download\\牛客网项目\\视频\\高级-03.mp4","D:\\download\\牛客网项目\\视频\\zzz.mp4");//total time:1640
        long end = System.currentTimeMillis();
        System.out.println("total time:" + (end - start));

    }
}
