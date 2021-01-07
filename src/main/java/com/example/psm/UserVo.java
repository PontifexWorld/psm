package com.example.psm;

import lombok.Data;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-11-10 13:04
 **/
@Data
public class UserVo {

    private Long id;

    private String name;

    private String password;

    private String email;

}