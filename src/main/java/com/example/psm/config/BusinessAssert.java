package com.example.psm.config;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @Description 断言类
 * @Author: liubicong
 * @Date: 2020/7/21 2:43 下午
 */
public interface BusinessAssert {

    /**
     * 创建异常
     * @param e  异常对象
     * @return 异常类
     */
    BusinessException newException(Object e);

    /**
     * 创建异常
     * @param e 异常对象
     * @param args  占位符信息
     * @return 异常类
     */
    BusinessException newException(Object e, Object... args);


    /**
     * 断言对象 param.
     * 如果对象为 null ，则抛出异常
     * @param param 待判断的对象
     */
    default void assertNotNull(Object param) {
        if (param==null) {
            throw newException(param);
        }
    }
    /**
     * 断言对象 param.
     * 如果对象为 null ，则抛出异常
     * 异常占位符:args 支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param param 待判断的对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object param,Object... args) {
        if (param==null) {
            throw newException(param,args);
        }
    }


    /**
     * 断言字符串 param.
     * 如果字符串为 blank，则抛出异常
     * @param param 待判断的字符串
     */
    default void assertNotBlank(String param) {
        if (StringUtils.isBlank(param)) {
            throw newException(param);
        }
    }

    /**
     * 断言字符串 param.
     * 如果字符串为 blank，则抛出异常
     * 异常占位符:args 支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param param 待判断字符串
     * @param args message占位符对应的参数列表
     */
    default void assertNotBlank(String param,Object... args) {
        if (StringUtils.isBlank(param)) {
            throw newException(param,args);
        }
    }

    /**
     * 断言结果是否为 true param.
     * 如果结果为 true，则抛出异常
     * @param param 待判断的字符串
     */
    default void assertBool(boolean param) {
        if (param) {
            throw newException(param);
        }
    }
    /**
     * 断言结果是否为 true param.
     * 如果结果为 true，则抛出异常
     * 异常占位符:args 支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param param 待判断字符串
     * @param args message占位符对应的参数列表
     */
    default void assertBool(boolean param,Object... args) {
        if (param) {
            throw newException(param,args);
        }
    }




}
