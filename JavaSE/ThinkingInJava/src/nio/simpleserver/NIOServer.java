package nio.simpleserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open(); // 创建Selector

        ServerSocketChannel sschannel = ServerSocketChannel.open();
        sschannel.configureBlocking(false); // 设置为非阻塞
        sschannel.register(selector, SelectionKey.OP_ACCEPT); // 注册channel

        ServerSocket serverSocket =  sschannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8888);
        serverSocket.bind(inetSocketAddress); // 绑定

        // 服务器会一直监听
        while (true){
            selector.select(); // 会一直阻塞，直到有就绪的channel
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();

                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    // 服务端的ServerSocketChannel响应客户端的连接
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromChannel(socketChannel));
                    socketChannel.close();

                } else if (key.isWritable()) {
                    // a channel is ready for writing

                }

                keyIterator.remove();
            }
        }
    }

    private static String readDataFromChannel(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();
        int bytesRead;

        while((bytesRead = channel.read(buffer)) != -1){ // 从 channel 中读取数据到 buffer 中
            buffer.flip(); // （对 buffer 而言）把写模式转换成读模式

            // 读取数据，并把数据转存至tmpData中
            char[] tmpData = new char[buffer.limit()];
            int i = 0;
            while(buffer.hasRemaining()){
                tmpData[i++] = (char)buffer.get();
            }
            buffer.clear();

            data.append(tmpData);
        }

        return data.toString();
    }

    private static boolean writeDataToChannel(SocketChannel channel) throws IOException {


        return true;
    }
}
