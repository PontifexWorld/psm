package com.example.lesscode.service.impl;

import com.example.lesscode.dao.SecurityUserDao;
import com.example.lesscode.domain.SecurityUserVO;
import com.example.lesscode.domain.UserOrgVO;
import com.example.lesscode.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p><b>账号管理</b></p>
 *
 * @author Chao.yy #2018年3月13日 下午10:20:43
 * @version V1.0
 */
@Service
public abstract class SecurityUserServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityUserDao dao;
    @Autowired
    private UserOrgService orgService;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * <p><b>Spring security 权限验证需要使用该方法获取用户信息</b></p>
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @author Chao.yy #2018年6月14日 上午11:06:25
     * @version V1.0
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String failureKey = "AUTHENTICATION:FAILURE:" + username;
        // 查询失败次数
//        if (redisTemplate.hasKey(failureKey)) {
//            BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(failureKey);
//            int failureTimes = (int) boundValueOperations.get();
//            if (failureTimes >= 5) {
//                throw new CustomAuthenticationException("密码连续错误次数过多，请在30分钟后重试！");
//            }
//        }

        // 查询用户信息
        SecurityUserVO securityUserVO = getUser(username);

        if (securityUserVO != null) {
            List<String> grantedAuthorities = dao.queryRoleByUserId(securityUserVO.getId());
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(grantedAuthorities.size());
            grantedAuthorities.forEach(rantedAuthority -> {
                        authorities.add(new SimpleGrantedAuthority(rantedAuthority));
                    }
            );
            securityUserVO.setAuthorities(authorities);
            if (securityUserVO.getOrgId() != null && !securityUserVO.getOrgId().isEmpty()) {
                Map<String, UserOrgVO> orgMap = orgService.queryOrgMap();
                UserOrgVO userOrgVO = orgMap.get(securityUserVO.getOrgId());
                if (userOrgVO != null) {
                    securityUserVO.setOrgName(userOrgVO.getName());
                    securityUserVO.setOrgTree(orgService.getOrgTree(securityUserVO.getOrgId(), orgMap));
                }
            }
            return securityUserVO;
        } else {
            throw new UsernameNotFoundException("该用户名不存在");
        }
    }

    /**
     * 查询用户
     * @param var1
     * @return
     * @throws UsernameNotFoundException
     */
    protected abstract SecurityUserVO getUser(String var1);

}
