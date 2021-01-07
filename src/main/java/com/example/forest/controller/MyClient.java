package com.example.forest.controller;

import com.alibaba.fastjson.JSON;
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


    @Get(url = "http://ditu.amap.com/service/regeo?longitude=${0}&latitude=${1}")
    Map getLocation(String longitude, String latitude);



    @Get(url = "http://dcfm.eastmoney.com/EM_MutiSvcExpandInterface/api/js/get?type=HSGTHIS&token=70f12f2f4f091e459a279469fe49eca5&filter=(MarketType=1)&js=var%20mDqblnZF={%22data%22:(x),%22pages%22:(tp)}&ps=10&p=1&sr=-1&st=DetailDate&rt=53441584")
    String methodFund(String url);
}