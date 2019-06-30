package com.http.tcp.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 类功能说明: TCP nio的客户端
 * 类修改者	创建日期2019/3/19
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class TCPNioClient {
    public static void main(String args[]){
        try {
            //第一个参数作为要连接的服务端的主机名或IP
            String server = "127.0.0.1";
            //第二个参数为要发送到服务端的字符串
            byte[] argument ="every day".getBytes();
            //创建一个信道，并设为非阻塞模式
            int servPort = 9090;
            //创建一个信道，并设为非阻塞模式
            SocketChannel clntChan  = SocketChannel.open();
            clntChan.configureBlocking(false);
            //向服务端发起连接
            if(!clntChan.connect(new InetSocketAddress(server,servPort))){
                //不断地轮询连接状态，直到完成连接
                while(!clntChan.finishConnect()){
                    //在等待连接的时间里，可以执行其他任务，以充分发挥非阻塞IO的异步特性
                    //这里为了演示该方法的使用，只是一直打印"."
                    System.out.print(".");
                }
            }
            //为了与后面打印的"."区别开来，这里输出换行符
            System.out.print("\n");
            //分别实例化用来读写的缓冲区
            ByteBuffer writeBuf = ByteBuffer.wrap(argument);
            ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
            //接收到的总的字节数
            int totalBytesRcvd = 0;
            //每一次调用read（）方法接收到的字节数
            int bytesRcvd;
            while(totalBytesRcvd<argument.length){
                //如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入信道
                if (writeBuf.hasRemaining()){
                    clntChan.write(writeBuf);
                }
                 //如果read（）接收到-1，表明服务端关闭，抛出异常
                if ((bytesRcvd = clntChan.read(readBuf)) == -1){
                    throw new SocketException("Connection closed prematurely");
                }
                //计算接收到的总字节数
                totalBytesRcvd += bytesRcvd;
                //在等待通信完成的过程中，程序可以执行其他任务，以体现非阻塞IO的异步特性
                //这里为了演示该方法的使用，同样只是一直打印"."
                System.out.print(".");
            }
            //打印出接收到的数据
            System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));
            //关闭信道
            clntChan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
