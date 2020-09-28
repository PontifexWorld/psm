package com.example.mq.controller;

import com.example.mq.domain.QueueConfig;
import com.example.mq.utils.RabbitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 13:19
 **/
@RestController
@RequestMapping("/config")
public class RabbitController {

    @Autowired
    private RabbitUtil rabbitUtil;

    /**
     * 创建交换器
     * @param config 交换器实体
     */
    @PostMapping("addExchange")
    public void addExchange(@RequestBody QueueConfig config) {
        rabbitUtil.addExchange(config.getExchangeType(), config.getExchangeName());
    }

    /**
     * 删除交换器
     * @param config 交换器实体
     */
    @PostMapping("deleteExchange")
    public void deleteExchange(@RequestBody QueueConfig config) {
        rabbitUtil.deleteExchange(config.getExchangeName());
    }

    /**
     * 添加队列
     * @param config 交换器实体
     */
    @PostMapping("addQueue")
    public void addQueue(@RequestBody QueueConfig config) {
        rabbitUtil.addQueue(config.getQueueName());
    }

    /**
     * 删除队列
     * @param config 交换器实体
     */
    @PostMapping("deleteQueue")
    public void deleteQueue(@RequestBody QueueConfig config) {
        rabbitUtil.deleteQueue(config.getQueueName());
    }

    /**
     * 清空队列数据
     * @param config 交换器实体
     */
    @PostMapping("purgeQueue")
    public void purgeQueue(@RequestBody QueueConfig config) {
        rabbitUtil.purgeQueue(config.getQueueName());
    }

    /**
     * 添加绑定
     * @param config 交换器实体
     */
    @PostMapping("addBinding")
    public void addBinding(@RequestBody QueueConfig config) {
        rabbitUtil.addBinding(config.getExchangeType(), config.getExchangeName(), config.getQueueName(), config.getRoutingKey(), false, null);
    }

    /**
     * 解除绑定
     * @param config 交换器实体
     */
    @PostMapping("removeBinding")
    public void removeBinding(@RequestBody QueueConfig config) {
        rabbitUtil.removeBinding(config.getExchangeType(), config.getExchangeName(), config.getQueueName(), config.getRoutingKey(), false, null);
    }

    /**
     * 创建头部类型的交换器
     * 判断条件是所有的键值对都匹配成功才发送到队列
     * @param config 交换器实体
     */
    @PostMapping("andExchangeBindingQueueOfHeaderAll")
    public void andExchangeBindingQueueOfHeaderAll(@RequestBody QueueConfig config) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue");
        header.put("bindType", "whereAll");
        rabbitUtil.andExchangeBindingQueue(RabbitUtil.ExchangeType.HEADERS, config.getExchangeName(), config.getQueueName(), null, true, header);
    }

    /**
     * 创建头部类型的交换器
     * 判断条件是只要有一个键值对匹配成功就发送到队列
     * @param config 交换器实体
     */
    @PostMapping("andExchangeBindingQueueOfHeaderAny")
    public void andExchangeBindingQueueOfHeaderAny(@RequestBody QueueConfig config) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue");
        header.put("bindType", "whereAny");
        rabbitUtil.andExchangeBindingQueue(RabbitUtil.ExchangeType.HEADERS, config.getExchangeName(), config.getQueueName(), null, false, header);
    }


}