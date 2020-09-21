package com.example.psm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug  https://blog.csdn.net/qq_33460562/article/details/96995548
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }



}