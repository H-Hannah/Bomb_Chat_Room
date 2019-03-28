package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class InputError {
    public static void inputError(Socket socket) throws IOException {
        PrintStream printStream=new PrintStream(socket.getOutputStream());
        printStream.println("输入格式错误!请按照以下格式输入!"+"\r\n"
                +"注册用户格式:[userName:用户名]"
                +"\r\n"+"群聊格式:[G:群聊信息]"
                +"\r\n"+"私聊格式:[P:userName-私聊信息]"
                +"\r\n"+"用户退出格式[包含886即可]");
    }
}
