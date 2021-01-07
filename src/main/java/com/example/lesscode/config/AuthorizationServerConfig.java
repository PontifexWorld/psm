package com.example.lesscode.config;
/**
 * <p><b>授权服务器配置</b></p>
 *
 * @author Chao.yy #2019年9月13日 下午10:20:43
 * @version V1.0
 */

import com.example.lesscode.service.impl.CustomUsernameUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableCaching
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 自定义用户验证数据
     */
    @Autowired
    private CustomUsernameUserDetailsServiceImpl userDetailsService;


    /**
     * 保存令牌数据存储对象
     */
    @Autowired
    private TokenStore tokenStore;

    @Value("${lesscode.oauth2.find-client-details-sql:SELECT CLIENT_ID, CLIENT_SECRET, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE FROM ms_auth_client_details ORDER BY CLIENT_ID}")
    private String findClientDetailsSql;

    @Value("${lesscode.oauth2.select-client-details-sql:SELECT CLIENT_ID, CLIENT_SECRET, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE FROM ms_auth_client_details  WHERE CLIENT_ID = ?}")
    private String selectClientDetailsSql;

    /**
     * <p><b>配置客户端请求参数</b></p>
     *
     * @param clients
     * @throws Exception
     * @author Chao.yy  # 2018年11月21日 下午3:24:06
     * @version V1.0.4
     * @see AuthorizationServerConfigurerAdapter#configure(ClientDetailsServiceConfigurer)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setFindClientDetailsSql(findClientDetailsSql);
        clientDetailsService.setSelectClientDetailsSql(selectClientDetailsSql);
        clients.withClientDetails(clientDetailsService);
    }


    /**
     * <p><b>配置token存储方式与加密形式</b></p>
     *
     * @param endpoints
     * @throws Exception
     * @author Chao.yy  # 2018年11月21日 下午3:27:02
     * @version V1.0.4
     * @see AuthorizationServerConfigurerAdapter#configure(AuthorizationServerEndpointsConfigurer)
     */

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 配置token存储
                .tokenStore(tokenStore())
                // 配置自定义的用户权限数据，不配置会导致token无法刷新
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                // 加载token配置
                .tokenServices(tokenServices());
    }



    @Bean
    public TokenStore tokenStore() {
        //使用redis存储令牌
        tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //支持刷新模式
        tokenServices.setSupportRefreshToken(true);
        //是用数据库存储token，这个框架默认已经实现了一个，我们没有特别的要求可以直接使用
        tokenServices.setTokenStore(tokenStore);
        //60*60*2token有效期设置2个小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        //60*60*12Refresh_token:12个小时
        tokenServices.setRefreshTokenValiditySeconds(43200);

        // token自定义
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //enable client to get the authenticated when using the /oauth/token to get a access token
        //there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
        oauthServer.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
