package com.example.psm;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-10-10 21:19
 **/
@Data
@Accessors(chain = true)
public class User {

    private Long id;

    private String account;

    private String password;

    private String email;
}