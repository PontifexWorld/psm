package com.example.mq.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 13:18
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class QueueConfig implements Serializable {

    /**
     * 交换器类型
     */
    private String exchangeType;

    /**
     * 交换器名称
     */
    private String exchangeName;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 路由键key
     */
    private String routingKey;

}