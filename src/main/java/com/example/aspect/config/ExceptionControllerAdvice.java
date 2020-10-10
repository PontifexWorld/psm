package com.example.aspect.config;

import com.example.aspect.domain.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-10 17:55
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> Exception(Exception e) {
        // 注意哦，这里返回类型是自定义响应体
        return new ResultVO<>(ResultCode.FAILED, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> APIExceptionHandler(NullPointerException e) {
        // 注意哦，这里返回类型是自定义响应体
        return new ResultVO<>(ResultCode.FAILED, e.getMessage());
    }


}