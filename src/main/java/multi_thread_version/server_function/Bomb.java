package multi_thread_version.server_function;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Bomb {
    public static void bomb(String s, Socket socket) throws IOException {
        String random = " ";
        String[] bomb = {"爱你", "想你", "喜欢你", "钟意你","宣你"};
        //随机产生数组下标
        int index = (int) (Math.random() * bomb.length);
        random = bomb[index];
        if (s.contains(random)) {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println("您已触发“别说爱我”主题炸弹，1分钟后方可继续聊天！");
            //静态方法中调用普通方法需要实例化对象
            Bomb b = new Bomb();
            b.runTime(60);
            socket.close();
            printStream.close();
        }
    }
    public void runTime(int time){
        int i = 0 ;
        new Thread().start();
        while (i<time) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
