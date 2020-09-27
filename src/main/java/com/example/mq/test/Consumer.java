package com.example.mq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * @author lsy
 * @version 1.0
 * @date 2020-09-27 16:40
 **/
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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
        //声明队列
        channel.queueDeclare("queue-hello", true, false, false, null);
        //声明绑定
        channel.queueBind("queue-hello", "ex-hello", "route-hello");

        //监听队列中的消息
        channel.basicConsume("queue-hello",true,new SimpleConsumer(channel));

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        conn.close();
    }





}