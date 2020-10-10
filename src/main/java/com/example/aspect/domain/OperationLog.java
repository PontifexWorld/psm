package com.example.aspect.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-10 16:57
 **/
@Data
public class OperationLog {

    private String operId;
    private String operModul;
    private String operType;
    private String operDesc;
    private String operMethod;
    private String operRequParam;
    private String operRespParam;
    private String operIp;
    private String operUri;
    private Date operCreateTime;
    private String operVer;

}