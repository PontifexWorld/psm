package com.example.lesscode.dao;


import com.example.lesscode.domain.UserOrgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 
 * <p><b>用户账号Dao</b></p>
 * @author Chao.yy  #2018年11月14日 上午9:29:20
 * @version V1.0.0
 */
@Mapper
public interface UserOrgDao {
     /**
     * 依据用户ID获取用户角色
     * @return
     */
    List<UserOrgVO> queryOrg();

}
