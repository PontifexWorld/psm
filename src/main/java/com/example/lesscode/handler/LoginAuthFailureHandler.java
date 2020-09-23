package com.example.lesscode.handler;

import com.example.lesscode.util.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/12/11 14:47
 * @Author: Qtl
 * @Description: 登录失败处理器，返回失败异常，该异常为第四步抛出异常
 */
@Component
public class LoginAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object> result = new HashMap<>(2);
        result.put("error","invalid_grant");
        result.put("error_description",exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        response.getWriter().write(JsonUtils.serializeJson(result));
    }
}
