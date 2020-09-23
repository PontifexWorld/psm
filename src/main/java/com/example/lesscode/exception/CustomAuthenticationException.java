package com.example.lesscode.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Date: 2019/12/12 15:08
 * @Author: Qtl
 * @Description: 自定义认证异常
 */
public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg) {
        super(msg);
    }

    public CustomAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

}
