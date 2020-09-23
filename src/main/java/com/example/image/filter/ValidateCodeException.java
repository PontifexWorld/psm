package com.example.image.filter;


import org.springframework.security.core.AuthenticationException;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-23 9:25
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}