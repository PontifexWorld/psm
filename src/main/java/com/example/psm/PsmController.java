package com.example.psm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-23 17:45
 **/
@RestController
@RequestMapping(value = "psm")
public class PsmController {


    @RequestMapping(value = "demo")
    public String psmTest() {
        System.out.println("进入控制层");
        return "success";
    }



}