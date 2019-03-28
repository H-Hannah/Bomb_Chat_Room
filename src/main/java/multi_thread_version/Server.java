package multi_thread_version;

import multi_thread_version.server_function.Verify;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerThread implements Runnable {
    //客户连接
    static Map<String, Socket> map = new ConcurrentHashMap<String, Socket>();

    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        //获取客户端的输入流并判断其输入合法性
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            String msg = null;
            while (true) {
                //如果客户端发来了消息，就进行判断并实现各个功能
                if (scanner.hasNext()) {
                    msg = scanner.next();
                    Verify.exmaine(msg,socket,map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
public class Server {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端的ServerSocket对象,等待客户端连接
        ServerSocket serverSocket = new ServerSocket(6666);
        //2、创建线程池，使能够处理多个客户端
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        //3、给客户端初始响应
        for (int i = 0; i < 30; i++) {
            System.out.println("欢迎来到炸弹聊天室...");
            //4、侦听客户端
            Socket socket = serverSocket.accept();
            System.out.println("有新朋友加入...");
            //5、启动线程
            executorService.execute(new ServerThread(socket));
        }
        //6、关闭线程池
        executorService.shutdown();
        //7、关闭服务器
        serverSocket.close();
    }
}

