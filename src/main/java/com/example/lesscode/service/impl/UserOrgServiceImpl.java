package com.example.lesscode.service.impl;
/**
 * <p><b>用户组织机构服务</b></p>
 *
 * @author Chao.yy #2019年9月13日 下午10:20:43
 * @version V1.0
 */

import com.example.lesscode.dao.UserOrgDao;
import com.example.lesscode.domain.UserOrgVO;
import com.example.lesscode.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOrgServiceImpl implements UserOrgService {
    @Autowired
    private UserOrgDao dao;

    @Override
    @Cacheable(value = "SECURITY_ORG", key = "'SECURITY_ORG_MAP'")
    public Map<String, UserOrgVO> queryOrgMap() {
        List<UserOrgVO> orgs = dao.queryOrg();
        //临时存放
        Map<String, UserOrgVO> orgMap = new HashMap<String, UserOrgVO>(orgs.size());
        for (UserOrgVO userOrgVO : orgs) {
            orgMap.put(userOrgVO.getId(), userOrgVO);
        }
        return orgMap;
    }

    /**
     * 递归获取用户组织机构树
     *
     * @param orgId
     * @param orgMap
     * @return
     */
    @Override
    public List<Map<String, String>> getOrgTree(String orgId, Map<String, UserOrgVO> orgMap) {
        List<Map<String, String>> orgTree = new ArrayList<>();
        while (true) {
            UserOrgVO orgVO = orgMap.get(orgId);
            if (orgVO != null) {
                Map<String, String> org = new HashMap<>();
                org.put("id", orgVO.getId());
                org.put("name", orgVO.getName());
                org.put("abbrName",orgVO.getAbbrName());
                orgTree.add(0, org);
                orgId = orgVO.getParentId();
            } else {
                break;
            }
        }
        return orgTree;
    }


/**    @Override public UserOrgVO getOrgTree(UserOrgVO userOrgVO, Map<String, UserOrgVO> orgMap) {
//获取父级对象
UserOrgVO parentOrgVO = orgMap.get(userOrgVO.getParentId());
if (parentOrgVO != null) {
List<UserOrgVO> children = new ArrayList<UserOrgVO>(1);
children.add(userOrgVO);
parentOrgVO.setChildren(children);
return getOrgTree(parentOrgVO, orgMap);
} else {
return userOrgVO;
}
}*/
}

