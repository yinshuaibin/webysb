package com.ysb.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author yinshuaibin
 * @date 2021/9/7 17:48
 * @description
 */
@Slf4j
public class SocketClient {

    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;
    private boolean connect = false;

    public SocketClient(){
        this.bootstrap = new Bootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, SocketProperties.lengthFieldOffset, SocketProperties.lengthFieldLength));
                            p.addLast(new ClientHandler());
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void connect(String ip, int port){
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
        channelFuture = bootstrap.connect(inetSocketAddress).addListener((ChannelFutureListener) futureListener -> {
            if (futureListener.isSuccess()) {
                log.info("连接服务成功: address:{},{}" + inetSocketAddress.getHostName() + inetSocketAddress.getPort());
                this.connect = true;
                ByteBuffer allocate = ByteBuffer.allocate(6);
                // 头
                allocate.put(SocketProperties.MSG_START);
                // 消息体长度
                allocate.putShort((short)3);
                // 本机地址
                allocate.put((byte) (200 & 0xff));
                // 命令
                allocate.put((byte)0xF0);
                channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(allocate.array()));
                channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(allocate.array()));
                channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(allocate.array()));
            } else {
                // 否则每隔10s重新连接
                log.error("连接服务失败,将在10s后重连 ----- host: {}, port: {}" + inetSocketAddress.getHostName() + inetSocketAddress.getPort());
                futureListener.channel().eventLoop().schedule(() -> connect(ip, port), 10, TimeUnit.SECONDS);
                this.connect = false;
            }
        });
    }

    public void sendMessage(String msg){
        if (this.connect){
            channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
        }else {
            log.error("还没有连接服务, 请先连接服务");
        }
    }

    public static void main(String[] args){
        SocketClient s = new SocketClient();
        s.connect("127.0.0.1", 9999);
    }

    /**
     * SimpleChannelInboundHandler使用这个时,注意数据的类型
     */
    private static class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            super.userEventTriggered(ctx, evt);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("client---");
            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            System.out.println("ip" + inetSocketAddress.getHostString());
            System.out.println("port" + inetSocketAddress.getPort());
            System.out.println("client---");
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf){
            log.info("接收到了服务器端的消息, 消息长度为:{}", byteBuf.readableBytes());
            if (byteBuf.readableBytes() > 0){
                System.out.println(byteBuf.getByte(0));
            }
        }

        /**
         * 当触发了通道没有激活的状态,则重连.
         * @param ctx 通道句柄上下文接口,关联着channel和pipeline
         * @throws Exception exception
         */
        @Override
        public void channelInactive(ChannelHandlerContext ctx){
            InetSocketAddress isa = (InetSocketAddress) ctx.channel().remoteAddress();
            SocketClient s = new SocketClient();
            s.connect(isa.getHostName(), isa.getPort());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
