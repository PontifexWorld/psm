package com.example.mq.controller;

import com.example.lesscode.util.JsonUtils;
import com.example.mq.domain.ConsumerInfo;
import com.example.mq.utils.RabbitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 12:54
 **/
@RestController
@RequestMapping(value = "consumer")
public class ConsumerController {

    public static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private SimpleMessageListenerContainer container;

    @Autowired
    private RabbitUtil rabbitUtil;


    /**
     * 添加队列到监听
     * @param consumerInfo 队列名称实体
     * @throws Exception 抛出异常
     */
    @RequestMapping("addQueue")
    public void  addQueue (@RequestBody ConsumerInfo consumerInfo) throws Exception {
        boolean existQueue = rabbitUtil.existQueue(consumerInfo.getQueueName());
        if (!existQueue) {
            throw new Exception("当前队列不存在");
        }
        container.addQueueNames(consumerInfo.getQueueName());
        logger.info("container-queue:{}", JsonUtils.serializeJson(container.getQueueNames()));
    }

    /**
     * 移除一个监听的队列
     * @param consumerInfo 队列名称实体
     */
    @RequestMapping("removeQueue")
    public void removerQueue(@RequestBody ConsumerInfo consumerInfo) {
        container.removeQueueNames(container.getQueueNames());
        logger.info("container-remove:{}",JsonUtils.serializeJson(container.getQueueNames()));
    }

    /**
     * 查询正在监听的队列名称
     */
    @RequestMapping("queryListener")
    public void queryListenerQueue() {
        String[] queueNames = container.getQueueNames();
        logger.info("container-remove:{}",JsonUtils.serializeJson(queueNames));
    }

}