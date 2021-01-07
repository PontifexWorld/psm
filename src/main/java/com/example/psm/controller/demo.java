package com.example.psm.controller;

import com.example.psm.PsmController;
import com.example.psm.UserVo;
import com.example.psm.config.MybatisPlusConfig;
import com.example.psm.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-12-28 14:25
 **/
@RestController
@RequestMapping(value = "psm")
public class demo {

        public static final Logger logger = LoggerFactory.getLogger(demo.class);

        @Autowired
        UserDao user;

        @RequestMapping(value = "demo1")
        public String psmTest(MultipartFile file) {
            System.out.println(file.getOriginalFilename()+"====---");
            System.out.println("进入控制层");

            return "success";
        }


}