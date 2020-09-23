package com.example.lesscode.handler;

import com.example.lesscode.service.impl.CustomClientDetailsService;
import com.example.lesscode.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/12/11 14:46
 * @Author: Qtl
 * @Description: 登录成功处理器 该方法用于验证client信息 并返回token信息。
 */
@Component
public class LoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private CustomClientDetailsService customClientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Basic")) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String tmp = header.substring(6);
            String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));

            String[] clientArrays = defaultClientDetails.split(":");
            String clientId = clientArrays[0].trim();
            String clientSecret = clientArrays[1].trim();


            ClientDetails clientDetails = customClientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId 不存在" + clientId);
                //判断  方言  是否一致
            } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("clientSecret 不匹配" + clientId);
            }
            TokenRequest tokenRequest = new TokenRequest(null, clientId, clientDetails.getScope(), "custom");

            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            Map<String,Object> tokenMap = new HashMap<>(6);
            tokenMap.put("access_token",token.getValue());
            tokenMap.put("token_type",token.getTokenType());
            tokenMap.put("refresh_token",token.getRefreshToken().getValue());
            tokenMap.put("expires_in",token.getExpiresIn());
            tokenMap.put("scope",token.getScope());
            tokenMap.put("username",token.getAdditionalInformation().get("username"));
            // 将手机验证码生成的token放入tokenStore 否则退出在tokenStore找不到token信息
            tokenStore.storeAccessToken(token, oAuth2Authentication);
            // 将token返回
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.serializeJson(tokenMap));
        }catch (Exception e){
            Map<String,Object> result = new HashMap<>(2);
            result.put("error","invalid_grant");
            result.put("error_description",e.getMessage());
            response.setStatus(500);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.serializeJson(result));
        }
    }


}
