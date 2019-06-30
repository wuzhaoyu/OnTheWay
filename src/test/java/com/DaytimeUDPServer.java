package com;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
/**
 * 类功能说明:
 * 类修改者	创建日期2019/3/18
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class DaytimeUDPServer {
    private final static int PORT = 13;
    public static void main(String[] args) {
            /*
    在java中网络通讯业称作为Socket(插座)通讯，要求通讯 的两台器都必须要安装Socket。

    不同的协议就有不同 的插座（Socket）

    UDP通讯协议的特点：
        1. 将数据极封装为数据包，面向无连接。
        2. 每个数据包大小限制在64K中
        3.因为无连接，所以不可靠
        4. 因为不需要建立连接，所以速度快
        5.udp 通讯是不分服务端与客户端的，只分发送端与接收端。


        比如： 物管的对讲机, 飞Q聊天、 游戏...

    udp协议下的Socket:

        DatagramSocket(udp插座服务)
        DatagramPacket(数据包类)
            DatagramPacket(buf, length, address, port)
            buf: 发送的数据内容
            length : 发送数据内容的大小。
            address : 发送的目的IP地址对象
            port : 端口号。

    发送端的使用步骤：
        1. 建立udp的服务。
        2. 准备数据，把数据封装到数据包中发送。 发送端的数据包要带上ip地址与端口号。
        3. 调用udp的服务，发送数据。
        4. 关闭资源。


    */
        try {
            //建立udp的服务 ，并且要监听一个端口。
            DatagramSocket  socket = new DatagramSocket(9090);

            //准备空的数据包用于存放数据。
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024
            //调用udp的服务接收数据
            socket.receive(datagramPacket); //receive是一个阻塞型的方法，没有接收到数据包之前会一直等待。 数据实际上就是存储到了byte的自己数组中了。
            System.out.println("接收端接收到的数据："+ new String(buf,0,datagramPacket.getLength())); // getLength() 获取数据包存储了几个字节。
            System.out.println("receive阻塞了我，哈哈");
            //关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
