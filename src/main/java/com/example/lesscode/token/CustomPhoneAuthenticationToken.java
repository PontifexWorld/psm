package com.example.lesscode.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Date: 2019/12/11 14:25
 * @Author: Qtl
 * @Description: 新建 （手机验证码登录用）CustomPhoneAuthenticationToken 继承 MyAuthenticationToken
 */
public class CustomPhoneAuthenticationToken extends CustomAuthenticationToken {

    public CustomPhoneAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomPhoneAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
