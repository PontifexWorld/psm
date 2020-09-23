package com.example.lesscode.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p><b>用户安全性验证信息</b></p>
 * @author Chao.yy  #2018年06月14日 上午11:25:55
 * @version V1.0
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class SecurityUserVO implements UserDetails {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 密码
     */
    private String password;
    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 组织机构ID
     */
    private String orgName;
    /**
     * 组织机构树
     */
    private List<Map<String, String>> orgTree;
    /**
     * 显示名
     */
    private String displayName;
    /**
     * 失效时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    /**
     * 密码失效时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date passwordExpiryDate;
    /**
     * 状态
     */
    private Integer availableFlag;
    /**
     * 排序
     */
    private Double serialIndex;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 用户拥有的权限(角色)集合
     */
    private List<GrantedAuthority> authorities;

    public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
     * 
     * <p><b>用户拥有的权限(角色)</b></p>
     * @author Chao.yy #2018年3月6日 下午5:41:22 
     * @version V1.0
     * @return 
     * @see UserDetails#getAuthorities()
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     *
     * <p><b>用户账号是否过期 </b></p>
     * <p>true：可用</p>
     * <p>false：禁用</p>
     * @author Chao.yy #2018年3月6日 下午5:41:26
     * @version V1.0
     * @return
     * @see UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        //未设置失效日期则默认为不限制
        if (this.getExpiryDate() == null) {
            return true;
        } else {
            Date currentDate = new Date();
            return currentDate.before(this.getExpiryDate());
        }
    }

    /**
     *
     * <p><b>用户账号是否没有被锁定  </b></p>
     * <p>true：可用</p>
     * <p>false：禁用</p>
     * @author Chao.yy #2018年3月6日 下午5:44:03
     * @version V1.0
     * @return
     * @see UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.getAvailableFlag() != 2;
    }

    /**
     *
     * <p><b>用户密码是否在有效期内</b></p>
     * <p>true：可用</p>
     * <p>false：禁用</p>
     * @author Chao.yy #2018年3月6日 下午5:44:14
     * @version V1.0
     * @return
     * @see UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        //未设置失效日期则默认为不限制
        if (this.getPasswordExpiryDate() == null) {
            return true;
        } else {
            Date currentDate = new Date();
            return currentDate.before(this.getPasswordExpiryDate());
        }
    }

    /**
     *
     * <p><b>用户是否可用</b></p>
     * <p>true：可用</p>
     * <p>false：禁用</p>
     * @author Chao.yy #2018年3月6日 下午5:44:23
     * @version V1.0
     * @return
     * @see UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return this.getAvailableFlag() != 2;
    }


}
