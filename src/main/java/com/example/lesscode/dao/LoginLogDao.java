package com.example.lesscode.dao;

import com.example.lesscode.domain.LoginLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Qtl
 * @Date: 2019/8/10 12:29
 * @Description: TODO
 */
@Repository
@Mapper
public interface LoginLogDao {

    /**
     * 保存登录日志
     * @param loginLogVO
     */
    void save(LoginLogVO loginLogVO);

}
