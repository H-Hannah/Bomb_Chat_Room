package multi_thread_version.server_function;

import java.net.Socket;
import java.util.Map;

public class ClientInfo {
    public static void clientInfo(String s, Socket socket, Map map){
        String userName=s.split("\\:")[1];
        map.put(userName,socket);
        System.out.println("【用户名为:"+userName+"】的客户端上线了!");
        System.out.println("当前在线人数为:"+map.size()+"人");
    }
}
