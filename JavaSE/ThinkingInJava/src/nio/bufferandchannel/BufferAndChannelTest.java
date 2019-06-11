package nio.bufferandchannel;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class BufferAndChannelTest {

    public static void main(String[] args) throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //分配缓冲区空间
        ByteBuffer buf = ByteBuffer.allocate(512);
        int bytesread;
        while ( (bytesread = inChannel.read(buf)) != -1) {
            System.out.println("bytesread: " + bytesread);
            buf.flip();  //将Buffer从写模式切换到读模式
            System.out.println("AFTER flip(), capacity:" + buf.capacity() + " ,position:" + buf.position() + " ,limit:" + buf.limit());

            System.out.print("[");
            while(buf.hasRemaining()){
                System.out.print((char)buf.get()); //每次读取一个字节（这种方式下出现中文会乱码，因为一个中文字符无法由一个字节表示）
            }
            System.out.println("]");

            buf.clear(); //“清空”缓冲区
            System.out.println("AFTER clear(), capacity:" + buf.capacity() + " ,position:" + buf.position() + " ,limit:" + buf.limit());
        }
        aFile.close();
    }
}
