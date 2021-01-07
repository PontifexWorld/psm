package com.example.psm.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * @Description 自定义异常（用于在程序中手动抛出）
 * @Author: liubicong
 * @Date: 2020/7/21 2:43 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;
    /**
     *  异常额外信息对象
     */
    private Object info;

    /**
     * 创建异常对象
     *
     * @param code    编码
     * @param message 异常信息
     * @return
     */
    public BusinessException(String code, String message, Object info) {
        super(message);
        this.code = code;
        this.info = info;
    }

    /**
     * 创建异常对象
     *
     * @param code    编码
     * @param message 异常信息
     * @param cause   异常cause
     */
    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 创建异常对象
     *
     * @param code    编码
     * @param message 异常信息
     * @return
     */
    public BusinessException(String code, String message, Object info, Object... args) {
        super(MessageFormat.format(message, args));
        this.code = code;
    }

    /**
     * 创建异常对象
     *
     * @param code    编码
     * @param message 异常信息
     * @param cause   异常cause
     */
    public BusinessException(String code, String message, Throwable cause, Object... args) {
        super(MessageFormat.format(message, args), cause);
        this.code = code;
    }

}
