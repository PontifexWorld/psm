package com.example.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-27 17:34
 **/
@RestController
@RequestMapping(value = "mqController")
public class mqController {

    @Autowired
    private HelloSender helloSender;

    @RequestMapping(value = "mq")
    public void hello() throws Exception {
        helloSender.send();
    }

}