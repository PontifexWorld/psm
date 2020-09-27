package com.example.lesscode.config;

import com.example.lesscode.filter.CustomPhoneLoginAuthenticationFilter;
import com.example.lesscode.handler.LoginAuthFailureHandler;
import com.example.lesscode.handler.LoginAuthSuccessHandler;
import com.example.lesscode.provider.CustomPhoneAuthenticationProvider;
import com.example.lesscode.service.impl.CustomPhoneUserDetailsServiceImpl;
import com.example.lesscode.service.impl.CustomUsernameUserDetailsServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <b>Spring Security 权限控制配置类</b>
 * </p>
 *
 * @author Chao.yy #2018年06月26日 下午4:21:43
 * @version V1.0
 */
@Configuration
@EnableResourceServer
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * // 自定义用户验证数据
     */
    @Autowired
    private CustomUsernameUserDetailsServiceImpl customUsernameUserDetailsService;

    @Autowired
    private CustomPhoneUserDetailsServiceImpl customPhoneUserDetailsService;

    /**
     * 装配登录成功处理器 生成token用 通用， 下方配置的时候不能用new 的形式加入 不然里面的接口注入会报空指针
     */
    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;

    /**
     * // 配置登录失败处理器
     */
    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
	 *
	 * <p>
	 * <b>声明不需要权限验证的资源</b>
	 * </p>
	 *
     * /captcha/smsCode 图片验证码
     * /captcha/image 图片
     * /oauth/sms  短信
     * /druid/**  druid 监控页面
     *
	 * @author Chao.yy # 2018年06月19日 下午4:34:28
	 * @version V1.0
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 忽略/error页面的拦截验证
		web.ignoring()
                .antMatchers("/captcha/smsCode/**","/captcha/image","/oauth/sms","/druid/**");
	}


	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().authorizeRequests().anyRequest().authenticated().and().csrf().disable();

        // 暂时注释掉图片验证码
        //http.addFilterBefore(new VerificationCodeFilter(authenticationFailureHandler()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 手机验证码登陆过滤器
     */
    @Bean
    public CustomPhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter() {
        CustomPhoneLoginAuthenticationFilter filter = new CustomPhoneLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthFailureHandler);
        return filter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(customPhoneAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(customUsernameUserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public CustomPhoneAuthenticationProvider customPhoneAuthenticationProvider() {
        CustomPhoneAuthenticationProvider provider = new CustomPhoneAuthenticationProvider();
        provider.setUserDetailsService(customPhoneUserDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }



    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {//验证失败返回JSON格式信息
        return (request, response, exception) -> {
            Map<String, Object> map = new HashMap<>(2);
            map.put("code", 401);
            map.put("message", "验证码错误");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        };
    }

    /**
     * <p>
     * <b>配置密码加密方式</b>
     * </p>
     *
     * @return
     * @author Chao.yy # 2018年11月21日 上午11:34:55
     * @version V1.0.4
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
