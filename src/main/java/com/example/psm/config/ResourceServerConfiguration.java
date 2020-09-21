package com.example.psm.config;

import com.example.psm.config.exception.SystemAccessDeniedHandler;
import com.example.psm.config.exception.SystemAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    private static final String DEMO_RESOURCE_ID = "*";

    @Resource
    private SystemAuthenticationEntryPoint systemAuthenticationEntryPoint;

    @Resource
    private SystemAccessDeniedHandler systemAccessDeniedHandler;

    @Resource
    private ResourceServerTokenServices systemTokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        resources.tokenServices(systemTokenServices)
                .authenticationEntryPoint(systemAuthenticationEntryPoint)
                .accessDeniedHandler(systemAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/login","/api/token/**").permitAll() // for login
                //.permitAll()  // swagger2
//                .antMatchers( "/webjars/**", "/resources/**", "/swagger-ui.html",
//                 "/swagger-resources/**", "/v2/api-docs").permitAll()
                .anyRequest()
                .authenticated();
    }


}
