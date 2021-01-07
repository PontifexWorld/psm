package com.example.psm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.psm.UserVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lsy
 * @version 1.0
 * @date 2020-11-10 12:42
 **/
@Mapper
public interface UserDao extends BaseMapper<UserVo> {


}