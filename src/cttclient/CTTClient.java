package cttclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CTTClient {

    private int port = 10001;// 端口号
    private String ipAddress;// ip地址
    private Socket socket;
    private OutputStream os;// 用来接收服务端的输出信息
    private InputStream is;// 用来向服务端发送信息

    public CTTClient(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void startConnet(){
        try {
            this.socket = new Socket(ipAddress,port);
            this.os = this.socket.getOutputStream();
            this.is = this.socket.getInputStream();
            new Thread(this.writeRun).start();
            new Thread(this.readRun).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向服务端发送信息
    private Runnable writeRun = new Runnable (){
        @Override
        public void run() {
            Scanner sca = new Scanner(System.in);
                try {
                    while (true){
                        String str = sca.next();
                        if(!UserAction.judgeMsg(str)){
                            System.out.println("你违反了游戏规则，请重输");
                            continue;
                        }
                        os.write(str.getBytes());
                        os.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    };

    //接收服务端发送的信息
    private Runnable readRun = new Runnable() {
        @Override
        public void run() {
            try {
                while (true){
                    byte [] temp = new byte[1024];
                    int read = is.read(temp);
                    if(read<0){
                        break;
                    }
                    String msg = new String(temp,0,read,"utf-8");
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };
}
