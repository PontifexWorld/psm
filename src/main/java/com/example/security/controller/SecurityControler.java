package com.example.security.controller;

//https://blog.csdn.net/vbirdbest/article/details/89600073
//https://blog.csdn.net/qq_37771475/article/details/86153799
//https://blog.csdn.net/u012702547/article/details/97233483?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~top_click~default-1-97233483.nonecase&utm_term=security

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControler {


    @GetMapping(value = {"/home","/"})
    public String home(){
        return "home";
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

}
