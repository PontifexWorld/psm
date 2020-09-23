package com.example.lesscode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Date: 2019/12/11 15:06
 * @Author: Qtl
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthClientDetails implements Serializable {

//    @ApiModelProperty(value = "客户端id", name = "clientId", required = true)
    private String clientId;
//    @ApiModelProperty(value = "客户端密码", name = "clientSecret", required = true)
    private String clientSecret;
//    @ApiModelProperty(value = "资源范围 传值格式示例 auth,audit", name = "resourceIds")
    private String resourceIds;
//    @ApiModelProperty(value = "资源范围 传值格式示例 auth，audit", name = "scopes")
    private String scopes;
//    @ApiModelProperty(value = "授权类型 （四种多选或单选） 传值示例 password,authorization_code,client_credentials,refresh_token", name = "authorizedGrantTypes", required = true)
    private String authorizedGrantTypes;
//    @ApiModelProperty(value = "code返回地址  示例（url为返回地址） url1,url2", name = "webServerRedirectUris")
    private String webServerRedirectUris;
//    @ApiModelProperty(value = "权限范围 示例 auth，audit", name = "authorities")
    private String authorities;
//    @ApiModelProperty(value = "token有效时间 秒", name = "accessTokenValidity",required = true)
    private Integer accessTokenValidity;
//    @ApiModelProperty(value = "刷新token有效时间 秒", name = "refreshTokenValidity",required = true)
    private Integer refreshTokenValidity;
//    @ApiModelProperty(hidden = true)
    private String additionalInformation;
    /**
     * 是否自动授权  默认false
     */
//    @ApiModelProperty(hidden = true)
    private String autoApprove;
//    @ApiModelProperty(hidden = true)
    private Boolean valid;

}
