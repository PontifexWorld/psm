package com.example.lesscode.config;

import com.example.lesscode.domain.SecurityUserVO;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/12/11 16:51
 * @Author: Qtl
 * @Description: 自定义token返回值
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUserVO userVO = (SecurityUserVO) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>(1);
        additionalInfo.put("username", userVO.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
