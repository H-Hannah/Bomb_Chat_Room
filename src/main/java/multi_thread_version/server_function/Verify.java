package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Verify {
    //声明为静态方法，便于被同一个包下的其他类调用
    //把传进来的msg当为s来用
    public static void exmaine(String s, Socket socket,Map map) throws IOException {
        //先处理客户端输入的字符串
        Pattern pattern = Pattern.compile("\r\n");
        Matcher matcher = pattern.matcher(s);
        s = matcher.replaceAll(" ");

        //开始判断客户端发送的消息
        //注册  注册格式：userName:用户名
        if (s.startsWith("userName:")){
            ClientInfo.clientInfo(s,socket,map);
        }

        //群聊  格式为：G:群聊消息
        else if (s.startsWith("G:")){
            Register.register(socket,map);
            GroupChat.groupChat(s,socket,map);
            //炸弹消息
            Bomb.bomb(s,socket);
        }

        //私聊  格式为： P:用户名-消息
        //暂时有问题 不是对方收到消息
        else if (s.startsWith("P:")&& s.contains("-")){
            Register.register(socket,map);
            PrivateChat.privateChat(s,socket,map);

        }


        //退出消息
        else if (s.contains("886")){
            //Register.register(socket,map);
            SayBye.sayBye(s,socket,map);
        }
        else {
            InputError.inputError(socket);
        }
    }
}
