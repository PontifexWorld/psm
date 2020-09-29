package com.example.psm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-09-28 21:22
 **/
@Data
public class OrderDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private Date updateTime;


}