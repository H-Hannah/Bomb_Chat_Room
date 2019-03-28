package single_thread_version;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) throws IOException {
        //1、客户端通过IP地址和端口号连接服务器端，返回其套接字socket对象
        Socket socket = new Socket("127.0.0.1",6666);

        //2、获取服务器端输出流给客户端的输入流，并向服务器端发送内容
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println("我是刚来的客户,我的端口号是："+socket.getLocalPort());
        //3、获取服务器端输入流，读取服务器的内容
        Scanner scanner = new Scanner(socket.getInputStream());
            //因为Scanner格式化输入是以空格作为分隔符的，一个空格就完成一次信息输入且它会自动跳过输入流开头的空格
            //为了避免服务器端发送的字符串中含有空格，使用useDelimiter方法将空格换为其他字符，这里换为换行符
        scanner.useDelimiter("\r\n");
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }

        //4、关闭流
        socket.close();

    }
}
