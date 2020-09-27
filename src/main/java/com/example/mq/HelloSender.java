package com.example.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-27 17:33
 **/
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello psm项目正在开发过程中。。。。" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}