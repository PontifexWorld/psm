package com.example.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-29 11:02
 **/
@RestController
@RequestMapping(value = "httpController")
public class HttpController {

    private MyClient myClient;

    @RequestMapping(value = "method")
    public String method(String token) {
        String forestUrl = "192.168.56.101";
        return myClient.request(forestUrl,token);
    }
}