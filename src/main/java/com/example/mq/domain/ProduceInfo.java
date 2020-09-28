package com.example.mq.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 10:24
 **/
@Data
public class ProduceInfo implements Serializable {

    private String exchangeName;

    private String routingKey;

    private String msg;

    public ProduceInfo() {}

    public ProduceInfo(String exchangeName, String routingKey, String msg) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.msg = msg;
    }

    private static ProduceInfo of(String exchangeName, String routingKey, String msg) {

        return new ProduceInfo(exchangeName,routingKey,msg);
    }


}