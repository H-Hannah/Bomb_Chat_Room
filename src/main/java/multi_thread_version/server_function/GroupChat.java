package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class GroupChat {
    public static void groupChat(String s, Socket socket,Map map) throws IOException {
        String str = s.split("\\:")[1];
        //将Map集合转为Ser集合
        Set<Map.Entry<String,Socket>> set = map.entrySet();
        //遍历set集合找到发起群聊信息的用户
        String userName = null;
        for (Map.Entry<String,Socket> entry:set){
            if (entry.getValue().equals(socket)){
                userName = entry.getKey();
                break;
            }
        }
        //遍历set集合将群聊信息发给每一个客户端
        for (Map.Entry<String,Socket> entry:set){
            //取得客户端的socket对象
            Socket client = entry.getValue();
            //取得client客户的输出流
            PrintStream printStream = new PrintStream(client.getOutputStream());
            printStream.println(userName+"在群聊里说："+str);
        }
    }
}
