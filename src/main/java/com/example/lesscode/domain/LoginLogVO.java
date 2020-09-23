package com.example.lesscode.domain;

import lombok.*;

import java.util.Date;

/**
 * <p><b>登录日志实体类</b></p>
 *
 * @author Chao.yy  #2019年08月07日 06:11:31
 * @version V1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class LoginLogVO implements java.io.Serializable {

    private String id;

    private String loginUsername;

    private String loginIp;

    private String filterUrl;

    private Integer loginStatus;

    private Date createTime;


}