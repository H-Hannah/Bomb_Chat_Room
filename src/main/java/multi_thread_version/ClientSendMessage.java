package multi_thread_version;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

//客户端向服务器端发送消息
public class ClientSendMessage implements Runnable {
    public static Socket socket;
    public ClientSendMessage(Socket socket){
        this.socket = socket;
    }
    public void run() {
        //获取服务器端输出流到我的输入流，并向其发送消息
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            //客户端从键盘输入消息
            Scanner scanner = new Scanner(System.in);
            while (true){
                String msg = null;
                if (scanner.hasNext()){
                    msg=scanner.next();
                    printStream.println(msg);
                }
                if (msg.equals("886")){
                    scanner.close();
                    printStream.close();
                    break;
                }
            }

            //当有输入时,不管怎么样我都能发出去，就看你给不给别人发了
//            while (scanner.hasNext()){
//                String str = scanner.next();
//                printStream.println(str);
//                //我想关闭了,自关闭
//                //运行时空指针异常
//                //boolean status = msg.contains("886");
//                if (str != null && str.contains("886")) {
//                    scanner.close();
//                    printStream.close();
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
