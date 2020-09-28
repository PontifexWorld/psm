package com.example.mq.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 13:02
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerInfo implements Serializable {

    private String queueName;


}