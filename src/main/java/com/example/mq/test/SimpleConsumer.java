package com.example.mq.test;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-27 16:41
 **/
public class SimpleConsumer extends DefaultConsumer {


    public SimpleConsumer(Channel channel) {
        super(channel);
    }


    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,BasicProperties properties, byte[] body) throws IOException {
        //接受从队列中发送的消息
        System.out.println(consumerTag);
        System.out.println("-----收到消息了---------------");
        System.out.println("消息属性为："+properties);
        System.out.println("消息内容为："+new String(body));
    }


}