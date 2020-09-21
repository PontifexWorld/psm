package com.example.psm.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> queryUserOwnedRoleCodes(@Param("userName") String userName);

}
