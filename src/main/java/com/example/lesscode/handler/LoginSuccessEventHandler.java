package com.example.lesscode.handler;

import com.example.lesscode.domain.SecurityUserVO;
import com.example.lesscode.service.LoginLogService;
import com.example.lesscode.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p><b>登录成功事件处理</b></p>
 *
 * @author Chao.yy  #2019年08月04日 上午10:05:09
 * @version V1.0
 */
@Slf4j
@Component
public class LoginSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginLogService service;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录成功事件处理方法.
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // 在Spring API中提供了一个非常便捷的工具类RequestContextHolder，能够在Controller中获取request对象和response对象，使用方法如下
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Authentication 有两个很重要的对象 1.Details  2.Pricipal  Details可以转换为WebAuthenticationDetails
        Authentication authentication = (Authentication) event.getSource();
        // 从ServletRequestAttributes中获取Request
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取请求的URL
        String requestURL = String.valueOf(request.getRequestURL());
        // 获取用户登录的真实IP
        String remoteHost = IPUtil.getRemoteHost(request);
        // 保存登陆日志
        if (authentication.getPrincipal() instanceof SecurityUserVO) {
            //增加用户信息存入redis 用户信息以map形式存入
            SecurityUserVO securityUserVO = (SecurityUserVO) authentication.getPrincipal();
            Field[] declaredFields = SecurityUserVO.class.getDeclaredFields();
            Map<String, Object> userProperty = new HashMap<String, Object>(declaredFields.length);
            for (Field field : declaredFields) {
                if ("orgTree".equals(field.getName())){
                    continue;
                }
                field.setAccessible(true);
                try {
                    userProperty.put(field.getName(), field.get(securityUserVO));
                } catch (IllegalAccessException e) {
                    log.error("用户对象" + field.getName() + "属性转Map异常", e);
                }
            }
            userProperty.remove("password");
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("SECURITY_USER_LIST" + ":" + securityUserVO.getUsername());
            boundValueOperations.set(userProperty);
            //清除失败次数记录
            String failureKey = "AUTHENTICATION:FAILURE:"+securityUserVO.getPhoneNo();
            if (redisTemplate.hasKey(failureKey)){
                redisTemplate.delete(failureKey);
            }
//            service.saveLoginLog(requestURL, securityUserVO.getDisplayName(), remoteHost, 1);
            // 输出日志
            log.info("用户：{} 登录成功", securityUserVO.getDisplayName());
        }
    }
}
