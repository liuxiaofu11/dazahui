package com.tengda.dazahui.system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author teswell
 * @Date 2021/1/7 16:56
 * @function
 */
public class IOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket  serverSocket  = new ServerSocket(8888);

        //(1)接受新连接线程
        new Thread(() ->{
            while (true){
                //(1) 阻塞方法获取新的连接
                try {
                    Socket socket = serverSocket.accept();
                    //(2)每一个新的连接都创建一个线程,负责读取数据
                    new Thread(() ->{
                        byte[]  data = new byte[1024];
                        try {
                            InputStream inputStream = socket.getInputStream();
                            while (true){
                                int len;
                                //(3) 按字节流方式读取数据
                                while ((len = inputStream.read(data)) !=-1){
                                    System.out.println(new String(data,0,len));
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
