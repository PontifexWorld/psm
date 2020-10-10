package com.example.aspect.domain;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-10 16:56
 **/
import lombok.Data;

import java.util.Date;

@Data
public class ExceptionLog {

    private String excId;
    private String excRequParam;
    private String operMethod;
    private String excName;
    private String excMessage;
    private String operUri;
    private String operIp;
    private String operVer;
    private Date operCreateTime;

}