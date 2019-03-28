package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Register {
    public static void register(Socket socket,Map map) throws IOException {
        //强迫当前客户端首先注册
        Set<Map.Entry<String,Socket>> set = map.entrySet();
        for (Map.Entry<String,Socket> entry:set){
            if (entry.getValue().equals(socket)){
                if (entry.getKey() ==null){
                    PrintStream printStream = new PrintStream(socket.getOutputStream());
                    printStream.println("请先进行注册！");
                    printStream.println("注册格式为：[userName:用户名]");
                }
            }
        }
    }
}
