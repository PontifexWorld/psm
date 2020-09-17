package com.example.psm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-11 12:42
 **/
@RestController
@RequestMapping("psm/app")
public class PsmController {


    @RequestMapping(value = "demo")
    public String demo(){
        System.out.println("恭喜进入控制层！！！！！");
        return "第一个springboot项目";
    }



}