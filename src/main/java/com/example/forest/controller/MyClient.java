package com.example.forest.controller;

import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.PostRequest;
import com.dtflys.forest.annotation.Request;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-29 10:42
 **/
public interface MyClient {

    @Request(
            url = "http://${forestUrl}:8081/psm/oms?access_token=${token}"
    )
    String request(@DataVariable("forestUrl") String forestUrl,@DataVariable("token") String token);



    @PostRequest(
            url = "http://${forestUrl}:8081/hello"
    )
    String simplePostRequest(@DataVariable("forestUrl") String forestUrl);



}