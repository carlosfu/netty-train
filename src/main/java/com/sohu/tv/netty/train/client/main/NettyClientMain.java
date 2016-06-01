package com.sohu.tv.netty.train.client.main;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.sohu.tv.netty.train.client.handler.ClientHiHandler;

/**
 * netty客户端
 * 
 * @author leifu
 * @Date 2016-5-29
 * @Time 下午4:13:42
 */
public class NettyClientMain {

    private final static int PORT = 8888;

    public static void main(String[] args) {
        // 客户端
        ClientBootstrap bootstrap = new ClientBootstrap();

        // 线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        // 管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("hiHandler", new ClientHiHandler());
                return pipeline;
            }
        });

        // 连接Netty服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", PORT));
        Channel channel = connect.getChannel();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入");
            channel.write(scanner.next());
        }

    }

}
