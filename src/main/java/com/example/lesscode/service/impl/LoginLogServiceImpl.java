package com.example.lesscode.service.impl;


import com.example.lesscode.dao.LoginLogDao;
import com.example.lesscode.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Qtl
 * @Date: 2019/8/10 12:28
 * @Description: 登录日志接口实现类
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao dao;

//    @Override
//    public void saveLoginLog(String  requestURL, String userName,String remoteAddress,Integer loginStatus) {
//            LoginLogVO loginLog = new LoginLogVO();
//            loginLog.setId(IDUtils.randomUuid());
//            loginLog.setLoginUsername(userName);
//            loginLog.setCreateTime(new Date());
//            loginLog.setLoginIp(remoteAddress);
//            loginLog.setLoginStatus(loginStatus);
//            loginLog.setFilterUrl(requestURL);
//            dao.save(loginLog);
//    }


}
