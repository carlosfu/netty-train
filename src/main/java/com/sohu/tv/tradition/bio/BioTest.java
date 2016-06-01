package com.sohu.tv.tradition.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio服务器测试一：无线程池
 * 
 * @author leifu
 * @Date 2016-5-30
 * @Time 下午9:46:27
 */
public class BioTest {

    private final static int PORT = 10010;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("server start");
        while (true) {
            // 获取一个套接字(阻塞)
            Socket socket = serverSocket.accept();
            System.out.println("新来了一个客户端: " + socket.getInetAddress().getHostName());
            // 业务处理
            handler(socket);
        }
    }

    /**
     * 读取数据
     * 
     * @param socket
     */
    private static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                // 读取数据(阻塞)
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("socket关闭");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
