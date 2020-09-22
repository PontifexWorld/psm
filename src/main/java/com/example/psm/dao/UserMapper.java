package com.example.psm.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.psm.domain.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> queryUserOwnedRoleCodes(@Param("userName") String userName);

}
