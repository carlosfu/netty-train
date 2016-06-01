package com.sohu.tv.netty.train.client.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 客户端handler
 * @author leifu
 * @Date 2016-5-29
 * @Time 上午10:20:23
 */
public class ClientHiHandler extends SimpleChannelHandler {

    /**
     * 接收消息
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("server messageReceived");
        
        String s = (String) e.getMessage();
        System.out.println("string message is " + s);

        super.messageReceived(ctx, e);
    }

    /**
     * 捕获异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("server exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 新建连接
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("server channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 连接必须是已经建立的，关闭通道的时候才会触发
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("server channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("server channelClosed");
        super.channelClosed(ctx, e);
    }

}
