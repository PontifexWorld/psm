package com.example.mq.controller;

import com.example.mq.domain.ProduceInfo;
import com.example.mq.utils.RabbitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 13:16
 **/
@RestController
@RequestMapping("/produce")
public class ProduceController {

    @Autowired
    private RabbitUtil rabbitUtil;

    /**
     * 发送消息到交换器
     * @param produceInfo
     */
    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody ProduceInfo produceInfo) {

        rabbitUtil.convertAndSend(produceInfo.getExchangeName(), produceInfo.getRoutingKey(), produceInfo);
    }


}