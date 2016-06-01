package com.sohu.tv.netty.train.server.main;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.sohu.tv.netty.train.server.handler.ServerHelloHandler;

/**
 * netty服务端
 * 
 * @author leifu
 * @Date 2016-5-29
 * @Time 上午10:10:56
 */
public class NettyServer {

    private final static int PORT = 8888;

    public static void main(String[] args) {
        // 服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // boss线程池负责监控端口，worker线程池负责数据读写
        ExecutorService bossPool = Executors.newCachedThreadPool();
        ExecutorService workerPool = Executors.newCachedThreadPool();

        // 设置niosocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(bossPool, workerPool));

        // 设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("helloHandler", new ServerHelloHandler());

                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(PORT));

        System.out.println("netty server start!");
    }

}
