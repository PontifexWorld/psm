package com.example.lesscode.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 
 * <p><b>登录日志实体类</b></p>
 * @author Chao.yy  #2019年08月06日 09:36:56
 * @version V1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
//@ApiModel(description = "组织机构实体对象")
public class UserOrgVO implements java.io.Serializable {

    @TableField("ID")
    private String id;
    @TableField("NAME")
    private String name;
    @TableField("ABBR_NAME")
    private String abbrName;
    @TableField("TYPE")
    private String type;
    @TableField("PARENT_ID")
    private String parentId;
    private List<UserOrgVO> children;

}