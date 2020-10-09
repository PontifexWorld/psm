package com.example.psm;

import com.example.lesscode.domain.UserOrgVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-23 17:45
 **/
@RestController
@RequestMapping(value = "psm")
public class PsmController {

    public static final Logger logger = LoggerFactory.getLogger(PsmController.class);

    @RequestMapping(value = "demo")
    public String psmTest() {
        System.out.println("进入控制层");
        return "success";
    }


    @RequestMapping(value = "oom")
    public String psmOom(){
        Long L1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            logger.info("logger info " +i);
            try {
                for (int j = 0; j < 10; j++) {
                   String sa = new String();
                   sa = "123123";
                }
                Thread.sleep(1000*2);
                logger.info("剩余执行个数{}",50000-i);
            } catch (InterruptedException e) {}
        }
        logger.info("操作人: {},一共执行了{}时间","lsy",System.currentTimeMillis()-L1);
        return "执行完成";
    }

    @RequestMapping(value = "oms")
    public OrderDTO psmOms(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreateTime(LocalDateTime.now());
        orderDTO.setUpdateTime(new Date());
        System.out.println(orderDTO.toString());
        return orderDTO;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        Hashtable table = new Hashtable<>();
        int a = 60;
        int b = 3;
        System.out.println(~a);
        System.out.println(a|=b);
        System.out.println(a&=b);
        System.out.println(a^=b);
    }


}