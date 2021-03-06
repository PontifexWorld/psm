package com.example.lesscode.controller;

/**
 * <p><b>用户信息访问</b></p>
 *
 * @author Chao.yy #2019年9月13日 下午10:20:43
 * @version V1.0
 */
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.lesscode.domain.SecurityUserVO;
import com.example.lesscode.service.LoginLogService;
import com.example.lesscode.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/oauth")
public class SecurityUserController {

    /**
     * 保存令牌数据
     */
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private LoginLogService logService;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping(value = "/userDetail")
    public Object user(Principal principal) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("SECURITY_USER_LIST" + ":" + principal.getName());
        return boundValueOperations.get();
    }

    @GetMapping(value = "/userInfo")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     */
    @RequestMapping("/logout")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        String tokenValue = request.getHeader("Authorization");
        if (tokenValue != null) {
            tokenValue = tokenValue.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        } else {
            tokenValue = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
        }
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isBlank(accessToken.getValue())) {
            // "退出失败，token 为空
        } else {
            //获取当前登录用户信息
            OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
            // 删除用户在线信息
            redisTemplate.delete("SECURITY_USER_LIST" + ":" + auth2Authentication.getName());
            // 从redis中删除access_token
            tokenStore.removeAccessToken(accessToken);
            // 从redis中删除refresh_token
            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
            tokenStore.removeRefreshToken(refreshToken);
            try {
                if(request.getHeader("referer") != null){
                    //转发到客户端页面
                    response.sendRedirect(request.getHeader("referer"));
                    // 记录退出日志
                    String requestURL = String.valueOf(request.getRequestURL());
                    // 获取用户登录的真实IP
                    String remoteHost = IPUtil.getRemoteHost(request);
                    SecurityUserVO securityUserVO = (SecurityUserVO) auth2Authentication.getPrincipal();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping(value = "/times")
    public Integer oauthTimes(String username) {
        String failureKey = "AUTHENTICATION:FAILURE:" + username;
        if (redisTemplate.hasKey(failureKey)) {
            BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(failureKey);
            return (int) boundValueOperations.get();
        }else {
            return 0;
        }
    }

}


