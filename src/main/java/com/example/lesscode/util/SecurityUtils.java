package com.example.lesscode.util;

import com.example.lesscode.domain.SecurityUserVO;
import com.example.lesscode.service.UserOrgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-24 9:40
 **/
public class SecurityUtils {

    /**
     * 获取当前用户名
     * @return 返回当前用户名
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前用户信息
     * @return 返回登录人的信息
     */
    public static SecurityUserVO getUser() {
        Authentication authentication = getAuthentication();
        SecurityUserVO securityUserVO = new SecurityUserVO();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                BeanUtils.copyProperties(authenticationToken.getPrincipal(),securityUserVO);
                securityUserVO.setPassword(null);
                return securityUserVO;
            } else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
                // 刷新token方式
                PreAuthenticatedAuthenticationToken authenticationToken = (PreAuthenticatedAuthenticationToken) authentication;
                BeanUtils.copyProperties(authenticationToken.getPrincipal(),securityUserVO);
                securityUserVO.setPassword(null);
                return securityUserVO;
            }
        }
        return securityUserVO;
    }

    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}