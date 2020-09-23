package com.example.lesscode.service.impl;

import com.example.lesscode.dao.SecurityUserDao;
import com.example.lesscode.domain.SecurityUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Date: 2019/12/11 14:42
 * @Author: Qtl
 * @Description:
 */
@Service
public class CustomPhoneUserDetailsServiceImpl extends SecurityUserServiceImpl {

    @Autowired
    private SecurityUserDao dao;

    @Override
    protected SecurityUserVO getUser(String var1) {
        // 手机验证码登录使用，根据手机号码查询用户信息
        SecurityUserVO authUser = dao.loadUserByPhoneNo(var1);
        if (authUser == null) {
            throw new UsernameNotFoundException("找不到该用户，手机号码有误：" + var1);
        }
        return authUser;
    }

}
