package multi_thread_version.server_function;

import java.net.Socket;
import java.util.Map;

public class SayBye {
    public static void sayBye(String s, Socket socket, Map map){
        //利用socket取得对应的Key值
        String userName=null;
        for(Object key:map.keySet()){
            if(map.get(key).equals(socket)){
                userName=(String) key;
                break;
            }
        }
        //2.将userName,Socket元素从map集合中删除
        map.remove(socket);
        //3.提醒服务器该客户端已下线
        System.out.println("用户:"+userName+"已下线!");
    }
}
