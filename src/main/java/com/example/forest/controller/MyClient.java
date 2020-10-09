package com.example.forest.controller;

import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Request;

import java.util.Map;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-29 10:42
 **/
public interface MyClient {

    @Request(
            url = "http://${forestUrl}:8081/httpController/method1?access_token=${token}"
    )
    String request(@DataVariable("forestUrl") String forestUrl,@DataVariable("token") String token);



    @Request(
            url = "https://www.baidu.com/",
            type = "get",
            dataType = "json"
    )
    String simplePostRequest();


    @Get(url = "http://ditu.amap.com/service/regeo?longitude=${0}&latitude=${1}")
    Map getLocation(String longitude, String latitude);
}