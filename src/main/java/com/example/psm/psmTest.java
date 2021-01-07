package com.example.psm;


import com.example.psm.utils.CustomizeUtil;
import com.example.psm.utils.RunnableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.activation.*;
/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-25 8:51
 **/
public class psmTest {

    private static final Logger logger =  LoggerFactory.getLogger(psmTest.class);

    private static int index = 1;

    public void call () {
        index++;
        call();
    }

    static String base = "String";


    // 泛型方法 printArray
    public static < E > void printArray( E[] inputArray )
    {
        // 输出数组元素
        for ( E element : inputArray ){
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }


    interface People {
        default void eat(){
            System.out.println("人吃饭");
        }
    }

    /*
     * 来源公众号：Java技术栈
     */
    interface Man {
        default void eat(){
            System.out.println("男人吃饭");
        }
    }

    /*
     *
     */
    interface Boy extends Man, People {

        @Override
        default void eat() {

        }
    }

    public static List<String> getBetweenDate(String start, String end){
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();

        List<String> betweenDate = getBetweenDate("2019-02-01","2020-04-02");
        Stream<String> distinct = betweenDate.stream().map(e -> {
            return e.substring(0,7).replace("-","")+CustomizeUtil.TenDaysMonth(Integer.parseInt(e.substring(8,10)));
        }).distinct();
        Iterator<String> iterator = distinct.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }


        System.out.println(betweenDate.size());


//        2019-02
//        2019-03
//        2019-04
//        2019-05
//        2019-06
//        2019-07
//        2019-08
//        2019-09
//        2019-10
//        2019-11
//        2019-12
//        2020-01
//        2020-02
//        2020-03
//        2020-04

        // 创建不同类型数组： Integer, Double 和 Character
//        Integer[] intArray = { 1, 2, 3, 4, 5 };
//        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
//        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
//
//        System.out.println( "Array integerArray contains:" );
//        printArray( intArray  ); // 传递一个整型数组
//
//        System.out.println( "\nArray doubleArray contains:" );
//        printArray( doubleArray ); // 传递一个双精度型数组
//
//        System.out.println( "\nArray characterArray contains:" );
//        printArray( charArray ); // 传递一个字符型型数组

        // RunnableUtils.runnableDemo();

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            String str = base + base;
//            base = str;
//            list.add(str);
//        }


//        List<Byte[]> list = new ArrayList<>();
//        int i = 0;
//        boolean flag = true;
//        while (flag) {
//            try {
//                i ++;
//                list.add(new Byte[1024*1024]);
//            } catch (Throwable e) {
//                logger.info("一共运行了 {} 次",i);
//                e.printStackTrace();
//                flag=false;
//            }
//
//        }




//        try {
//            psmTest psmTest = new psmTest();
//            psmTest.call();
//        } catch (Throwable e) {
//            logger.info("stack deep: "+ index);
//            e.printStackTrace();
//        }

//        Long L1 = System.currentTimeMillis();
//        HashMap map = new HashMap();
//
//        for (int i = 0; i < 50000 ; i++) {
//            logger.trace("logger.trace"+i);
//            logger.debug("logger.debug"+i);
//            logger.info("logger.info"+i);
//            logger.warn("logger.warn"+i);
//            logger.error("logger.error"+i);
//        }
//
//        try {
//            Thread.sleep(1000*10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        logger.info("打印结束一共耗时{}时间",System.currentTimeMillis()-L1);
//        logger.info("操作人:{},打印结束耗时时长:{}","小院子",System.currentTimeMillis()-L1);
    }









}