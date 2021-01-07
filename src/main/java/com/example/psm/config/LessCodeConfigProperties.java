package com.example.psm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Description 对应配置文件属性的属性父类
 * @Author: liubicong
 * @Date: 2020/7/23 8:48 下午
 */
@Data
public class LessCodeConfigProperties {
    /**
     * 特殊字符过滤是否启用
     */
    private boolean characterFilterEnable = true;
    /**
     * 特殊字符黑名单|分隔
     */
    private String characterFilter = "insert|select|delete|update|drop|create|and|exec|count|or|truncate|char|declare|join|unicode|nchar|substring|chr|mid|master|<|>|\\u|/*|*/|,|;|$|'|\"|\\|0x0d|0x0a";

    /**
     * 跨域请求过滤是否启用
     */
    private boolean corsFilterEnable = true;
    /**
     * 允许跨域请求来源|分隔 默认全部
     */
    private String allowedOrigin = "*";
    /**
     * security 不拦截白名单 |分隔
     */
    private String securityIgnoredPath;
}
