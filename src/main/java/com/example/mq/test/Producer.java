package com.example.mq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * @author lsy
 * @version 1.0
 * @date 2020-09-27 16:39
 **/
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        //连接RabbitMQ服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("192.168.56.101");
        factory.setPort(5672);
        //创建一个连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明交换器
        channel.exchangeDeclare("ex-hello","direct");
        //发送的消息内容
        byte[] messageBodyBytes = "psm项目正在启动中！".getBytes();
        channel.basicPublish("ex-hello", "route-hello", null, messageBodyBytes);
        //关闭通道
        channel.close();
        conn.close();
    }


}