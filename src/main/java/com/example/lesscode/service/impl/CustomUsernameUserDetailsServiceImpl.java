package com.example.lesscode.service.impl;

import com.example.lesscode.dao.SecurityUserDao;
import com.example.lesscode.domain.SecurityUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Date: 2019/12/11 14:39
 * @Author: Qtl
 * @Description:
 */
@Service
public class CustomUsernameUserDetailsServiceImpl extends SecurityUserServiceImpl {


    @Autowired
    private SecurityUserDao dao;

    @Override
    protected SecurityUserVO getUser(String var1) {
        // 账号密码登录根据用户名查询用户
        SecurityUserVO authUser = dao.loadUserByUsername(var1);
        if (authUser == null) {
            throw new UsernameNotFoundException("找不到该用户,用户名：" + var1);
        }
        return authUser;
    }

}
