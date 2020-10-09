package com.example.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-29 11:02
 **/
@RestController
@RequestMapping(value = "httpController")
public class HttpController {

    @Autowired
    private MyClient myClient;

    @RequestMapping(value = "method")
    public String method(String token) {
        String forestUrl = "127.0.0.1";
        return myClient.request(forestUrl,token);
    }


    @RequestMapping(value = "method1")
    public Map method1() {
        System.out.println("---method1--");
        return myClient.getLocation("121.475078", "31.223577");
    }
}