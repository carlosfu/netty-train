package com.sohu.tv.netty.train.server.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 服务端handler
 * @author leifu
 * @Date 2016-5-29
 * @Time 上午10:20:23
 */
public class ServerHelloHandler extends SimpleChannelHandler {

    /**
     * 接收消息
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("client messageReceived");
        // System.out.println("message is: " + e.getMessage());

        // 方法一:直接解析MessageEvent
        // ChannelBuffer message = (ChannelBuffer) e.getMessage();
        // String s = new String(message.array());
        // System.out.println("string message is " + s);

        // 方法二:在NettyServer的管道中定义decoder,详见StringDecoder
        String s = (String) e.getMessage();
        System.out.println("string message is " + s);

        // 回写消息
        // 方法一:直接发送ChannelBuffer
        String reply = "hello";
//        ChannelBuffer replyBuffer = ChannelBuffers.copiedBuffer(reply.getBytes());
//        ctx.getChannel().write(replyBuffer);
        // 方法二：在NettyServer的管道中定义encoder,详见StringDecoder
        ctx.getChannel().write(reply);

        super.messageReceived(ctx, e);
    }

    /**
     * 捕获异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("client exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 新建连接
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 连接必须是已经建立的，关闭通道的时候才会触发
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelClosed");
        super.channelClosed(ctx, e);
    }

}
