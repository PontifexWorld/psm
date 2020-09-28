package com.example.mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 12:48
 **/
@Configuration
public class DynamicConsumeListener {

    public static final Logger logger = LoggerFactory.getLogger(DynamicConsumeListener.class);


    @Bean
    public Queue queue001() {
        return new Queue("hello", true);//队列持久化
    }

    /**
     * 使用SimpleMessageListenerContainer实现动态监听
     * @param connectionFactory 连接通道类
     * @return 返回监听对象
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue001());
        Map<String, Object> args = new HashMap<>();
        args.put("module","订单模块");
        args.put("fun","发送消息");
        container.setConsumerArguments(args);
        container.setMessageListener(message -> {
            if ("hello".equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println(message.getMessageProperties().getConsumerQueue());
            }
            logger.info("ConsumerMessageListen，收到消息: {}", message.toString());
            logger.info("ConsumerMessageListen，收到消息: {}", message.getMessageProperties());

        });
        return container;
    }

}