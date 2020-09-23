package com.example.lesscode.handler;

import com.example.lesscode.service.LoginLogService;
import com.example.lesscode.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 *
 * <p><b>登录失败事件处理</b></p>
 * @author Chao.yy  #2019年08月04日 上午10:05:09
 * @version V1.0
 */
@Slf4j
@Component
public class LoginFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Autowired
    private LoginLogService service;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登录失败处理方法
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        // 在Spring API中提供了一个非常便捷的工具类RequestContextHolder，能够在Controller中获取request对象和response对象，使用方法如下
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 认证异常对象
        AuthenticationException authenticationException = event.getException();
        // Authentication 有两个很重要的对象 1.Details  2.Pricipal  Details可以转换为WebAuthenticationDetails
        Authentication authentication = (Authentication) event.getSource();
        // 从ServletRequestAttributes中获取Request
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取请求的URL
        String requestURL = String.valueOf(request.getRequestURL());
        // 获取用户登录的真实IP
        String remoteHost = IPUtil.getRemoteHost(request);
        // 保存登陆日志
        String username = authentication.getName();
        String failureKey = "AUTHENTICATION:FAILURE:"+username;
        //记录失败次数
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(failureKey);
        int failureTimes = 0;
        if (redisTemplate.hasKey(failureKey)){
            failureTimes = (int) boundValueOperations.get();
        }
        boundValueOperations.set(++failureTimes,1800, TimeUnit.SECONDS);
        if(!"access-token".equals(username) && requestURL.contains("oauth/token") && requestURL.contains("oauth/sms")){
//            service.saveLoginLog(requestURL, username,remoteHost,0);
        }
        // 输出日志
        log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
    }
}
