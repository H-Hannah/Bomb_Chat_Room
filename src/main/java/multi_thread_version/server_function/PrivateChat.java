package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class PrivateChat {
    public static void privateChat(String s, Socket socket,Map map) throws IOException {
        int userName = socket.getPort();
        //取得用户名
        String userName1 = s.split("\\:")[1].split("-")[0];
        //保存私聊消息
        String str = s.split("\\:")[1].split("-")[1];
        //发送私聊消息
//        Set<Map.Entry<String,Socket>> set=map.entrySet();
//        for(Map.Entry<String,Socket> entry:set){
//            if(entry.getValue().equals(socket)){
//                userName=entry.getKey();
//                break;
//            }
//        }
        //取得私聊用户名对应的客户端
        Socket client = (Socket) map.get(userName1);
        //获取私聊客户端的输出流,将私聊信息发送到指定客户端
        PrintStream printStream=new PrintStream(client.getOutputStream());
        printStream.println("端口号为“"+userName+"”的人私聊你说:"+str);
    }
}
