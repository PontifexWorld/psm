package com.example.lesscode.service;


import com.example.lesscode.domain.UserOrgVO;

import java.util.List;
import java.util.Map;

/**
 * <p><b>组织机构服务接口</b></p>
 *
 * @author Chao.yy #2019年08月06日 09:36:733
 * @version V1.0
 */

public interface UserOrgService {

    /**
     * 查询组织机构，已OrgID为key
     *
     * @return
     */
    Map<String, UserOrgVO> queryOrgMap();

    /**
     * 依据所属部门获取组织树
     * @param orgId
     * @param orgMap
     * @return
     */
    List<Map<String,String>> getOrgTree(String orgId, Map<String, UserOrgVO> orgMap);

}
