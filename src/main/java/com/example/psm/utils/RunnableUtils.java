package com.example.psm.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-26 14:52
 **/
public class RunnableUtils {


    public static final Logger logger = LoggerFactory.getLogger(RunnableUtils.class);


    /**
     * java 8 多线程
     * @return
     */
    public static String runnableDemo() {

        Map<String, String> A = new HashMap<>(8);
        Map<String,String> B = new HashMap<>(8);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("RepeatPowerCutStatisticThreadPool-%d").build();
        /**
         * int corePoolSize 核心线程池数量
         * int maximumPoolSize 最大线程池数量
         * long keepAliveTime 保持心跳时间
         * TimeUnit unit 时间单位
         * BlockingQueue<Runnable> workQueue 任务队列
         * ThreadFactory threadFactory 异常工厂
         * RejectedExecutionHandler handler
         */
        ExecutorService executorService = new ThreadPoolExecutor(2,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        List<Runnable> taskList = new ArrayList<>(2);
        taskList.add(() -> A.put("线程1",methodOne()));
        taskList.add(() -> B.put("线程2",methodTwo()));
        CompletableFuture[] futures = taskList.stream().map(task -> CompletableFuture.runAsync(task, executorService)).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        executorService.shutdown();
        logger.info("任务计算结果："+ A);
        logger.info("任务计算结果："+ B);
        return "多线程计算结束";
    }

    public static String methodOne() {
        for (int i = 0; i < 10 ; i++) {
            logger.info("{}正在计算{}","线程一",i);
        }
        return "线程一";
    }


    public static String methodTwo(){
        for (int i = 0; i < 10 ; i++) {
            logger.info("{}正在计算{}","线程二",i);
        }
        return "线程二";
    }














}