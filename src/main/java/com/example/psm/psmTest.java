package com.example.psm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-25 8:51
 **/
public class psmTest {

    private static final Logger logger =  LoggerFactory.getLogger(psmTest.class);


    public static void main(String[] args) {
        Long L1 = System.currentTimeMillis();
        HashMap map = new HashMap();

        for (int i = 0; i < 50000 ; i++) {
            logger.trace("logger.trace"+i);
            logger.debug("logger.debug"+i);
            logger.info("logger.info"+i);
            logger.warn("logger.warn"+i);
            logger.error("logger.error"+i);
        }

        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("打印结束一共耗时{}时间",System.currentTimeMillis()-L1);
        logger.info("操作人:{},打印结束耗时时长:{}","小院子",System.currentTimeMillis()-L1);
    }









}