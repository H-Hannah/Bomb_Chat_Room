package multi_thread_version;

import java.io.IOException;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws IOException {
        //1、客户端要连接服务器，返回socket对象
        Socket socket = new Socket("127.0.0.1",6666);
        System.out.println("服务器您好，我是客户端："+socket.getLocalPort());

        //2、创建读取服务器端消息的线程和给服务器端发送消息的线程
        Thread read = new Thread(new ClientReadMessage(socket));
        Thread send = new Thread(new ClientSendMessage(socket));

        //3、启动线程
        read.start();
        send.start();
    }
}
