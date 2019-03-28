package multi_thread_version;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//客户端读取消息线程
public class ClientReadMessage implements Runnable {
    private Socket socket;
    public ClientReadMessage(Socket socket){
        this.socket = socket;
    }
    public void run() {
        try {
            //获取服务器端输入流，从而将消息显示到客户端
            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNext()){
                System.out.println("【服务器】"+"\r\n"+scanner.next());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
