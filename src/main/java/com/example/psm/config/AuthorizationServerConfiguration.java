package com.example.psm.config;

import com.example.psm.config.token.SystemJdbcTokenStore;
import com.example.psm.config.token.SystemTokenServices;
import com.example.psm.config.user.SystemUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SystemUserDetailsService systemUserDetailsService;

    @Resource
    private DataSource dataSource;

    @Bean
    public TokenStore jdbcTokenStore() {
        return new SystemJdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    // 定义客户端
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端, 用于client认证
        clients.withClientDetails(clientDetails());
    }


    // 定义令牌token
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(jdbcTokenStore())  // 数据库保存token
                .authenticationManager(authenticationManager)
                .userDetailsService(systemUserDetailsService);

        // 配置TokenServices参数 自定义SystemTokenServices 的实现
        SystemTokenServices tokenServices = new SystemTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setReuseRefreshToken(false);  // 每次刷新token 都会重新生成新的refresh token
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7)); // 刷新token有效时(天)
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(2)); //token有效时间(小时)
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.SECONDS.toSeconds(600)); // 刷新token有效时 测试
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.SECONDS.toSeconds(60)); //token有效时间 测试
        endpoints.tokenServices(tokenServices);
    }

    // 定义令牌端点上的安全性约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients();
    }

}
