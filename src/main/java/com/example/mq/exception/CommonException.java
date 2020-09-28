package com.example.mq.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 16:04
 **/
@Data
@AllArgsConstructor
public class CommonException extends RuntimeException{

    /**错误码*/
    private String code;

    /**错误信息*/
    private String msg;

    /**错误对象*/
    private Object data;


}