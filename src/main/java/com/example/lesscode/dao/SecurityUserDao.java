package com.example.lesscode.dao;


import com.example.lesscode.domain.AuthClientDetails;
import com.example.lesscode.domain.SecurityUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 
 * <p><b>用户账号Dao</b></p>
 * @author Chao.yy  #2018年11月14日 上午9:29:20
 * @version V1.0.0
 */
@Mapper
public interface SecurityUserDao  {
    /**
     * 查询最大的排序字段
     * @param username
     * @return
     */
    SecurityUserVO loadUserByUsername(String username);

    /**
     * 依据用户ID获取用户角色
     * @param userId
     * @return
     */
    List<String> queryRoleByUserId(String userId);

    /**
     * 依据用户ID获取用户角色
     * @param orgId
     * @return
     */
    List<Map<String,String>> queryOrg(String orgId);

    /**
     * 依据手机号码查询用户
     * @param phoneNo
     * @return
     */
    SecurityUserVO loadUserByPhoneNo(String phoneNo);

    /**
     * 根据Id查询客户端详情
     * @param clientId
     * @return
     */
    AuthClientDetails selectClientDetailsByClientId(String clientId);
}
