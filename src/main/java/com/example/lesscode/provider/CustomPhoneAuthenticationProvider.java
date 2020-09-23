package com.example.lesscode.provider;

import com.example.lesscode.token.CustomPhoneAuthenticationToken;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @Date: 2019/12/11 14:33
 * @Author: Qtl
 * @Description: 手机验证码登录
 */
public class CustomPhoneAuthenticationProvider extends CustomAbstractUserDetailsAuthenticationProvider{

    private UserDetailsService userDetailsService;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, Authentication authentication) throws AuthenticationException {

        if(authentication.getPrincipal() == null){
            throw new BadCredentialsException(this.messages.getMessage("CustomPhoneAuthenticationProvider.badPrincipal", "Bad badPrincipal"));
        }
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(this.messages.getMessage("CustomPhoneAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String phoneCode = authentication.getCredentials().toString();
            String  phoneNumber = (String) authentication.getPrincipal();

            // 从reids中取出手机验证码
            String smsKey = "AUTHENTICATION:SMS:"+ phoneNumber;
            String redisPhoneCode = "";
            BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(smsKey);
            if (redisTemplate.hasKey(smsKey)){
                redisPhoneCode = (String) boundValueOperations.get();
                // 从redis中删除手机验证码
//                redisTemplate.delete(smsKey);
            }

            // 校验验证码
            if (!redisPhoneCode.equals(phoneCode)) {
                throw new BadCredentialsException(this.messages.getMessage("CustomPhoneAuthenticationProvider.badCredentials", "Bad phoneCode"));
            }
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        CustomPhoneAuthenticationToken result = new CustomPhoneAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String phone, Authentication authentication) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(phone);
        } catch (UsernameNotFoundException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomPhoneAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
