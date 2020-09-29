package com.example.forest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-29 10:58
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpInfo implements Serializable {

    /*请求类型 http https*/
    private String heardType;
    /*ip地址*/
    private String host;
    /*端口号*/
    private String sort;
    /*方法路径*/
    private String methodPath;
    /*完成的url路径*/
    private String path;

    public static HttpInfo of(String heardType,String host,String sort,String methodPath,String path) {

        return new HttpInfo(heardType,host,sort,methodPath,path);
    }

}