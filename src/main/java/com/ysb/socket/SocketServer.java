package com.ysb.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * @author yinshuaibin
 * @date 2021/9/7 16:56
 * @description
 */
@Slf4j
public class SocketServer {

    private int port;

    public SocketServer(int port) {
        this.port = port;
    }

    public void run() {
        // 用于处理服务器端接受客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 进行网络通信(读写)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            // 辅助工具类, 用于服务器通道的一系列配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 绑定两个线程组
            bootstrap.group(bossGroup, workerGroup)
                    // 指定NIO模式
                    .channel(NioServerSocketChannel.class)
                    // 保持连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    /**
                     * ChannelOption.SO_BACKLOG
                     * 服务端TCP内核维护有两个队列, A,B 客户端向服务器端connect时, 会发送
                     * 带有SYN标志的包(第一次握手), 服务器端接收到客户端发送的SYN时, 向客户端发送SYN ACK确认(第二次握手),
                     * 此时TCP内核模块把客户端连接加入到A队列中, 然后服务器接收到客户端发送的ACK时(第三次握手),TCP内核模块
                     * 把客户端连接从A队列移动到B队列, 连接完成,应用程序的accept会返回, 也就是说accept从B队列取出完成了
                     * 三次握手的连接,A队列和B队列的长度之和就是backlog,当A,B队列的长度之和大于ChannelOption.SO_BACKLOG时
                     * 新的连接将会被TCP内核拒绝, 所以, 如果backlog过小,可能会出现accept速度跟不上,A,B队列满了,导致新的
                     * 客户端无法连接, 要注意的是, backlog对程序支持的连接数量并无影响,backlog影响的只是还没有被accept取出的连接
                     */
                    // TCP缓冲区
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 接收数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,SocketProperties.lengthFieldOffset, SocketProperties.lengthFieldLength));
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

        /**
         * 客户端连接上, 消息到达前
         * @param ctx ChannelHandlerContext
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("server---");
            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            System.out.println("ip" + inetSocketAddress.getHostString());
            System.out.println("port" + inetSocketAddress.getPort());
            System.out.println("server---");
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            log.info("接收到了客户端的消息, 消息长度为:{}", byteBuf.readableBytes());
            byte aByte = byteBuf.getByte(0);
            System.out.println("头:" + aByte);
            short aShort = byteBuf.getShort(1);
            System.out.println("长度:" + aShort);
            byte address = byteBuf.getByte(3);
            System.out.println("发送过来的地址:" + (address & 0xff));
            byte cmd = byteBuf.getByte(4);
            System.out.println("cmd命令:" + (int)cmd);
            // 初始化（allocate）–> 写入数据（read / put）–> 转换为写出模式（flip）–> 写出数据（get）–> 转换为写入模式（compact）–> 写入数据（read / put）
            ByteBuffer allocate = ByteBuffer.allocate(6);
            // 头
            allocate.put(SocketProperties.MSG_START);
            // 消息体长度
            allocate.putShort((short)3);
            // 地址
            allocate.put((byte) (200 & 0xff));
            // 命令
            allocate.put((byte)0xF0);
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(allocate.array()));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

        /**
         * 有客户端离线
         * @param ctx ChannelHandlerContext
         * @throws Exception exception
         */
        @Override
        public void handlerAdded(ChannelHandlerContext ctx)throws Exception{
            super.handlerRemoved(ctx);
        }

        /**
         * 有客户端断开
         * @param ctx ChannelHandlerContext
         * @throws Exception exception
         */
        @Override
        public void handlerRemoved(ChannelHandlerContext ctx)throws Exception{
            super.handlerRemoved(ctx);
        }
    }


    public static void main(String[] args){
        SocketServer socketServer = new SocketServer(9999);
        socketServer.run();
    }
}
