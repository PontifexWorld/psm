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

    //    @ApiModelProperty(value = "主键")
//    @OpenAPIDefinition()
    @TableField("ID")
    private String id;
    //    @ApiModelProperty(value = "机构名称")
    @TableField("NAME")
    private String name;
    //    @ApiModelProperty(value = "缩写")
    @TableField("ABBR_NAME")
    private String abbrName;
    //    @ApiModelProperty(value = "机构类型")
    @TableField("TYPE")
    private String type;
    //    @ApiModelProperty(value = "父级机构ID")
    @TableField("PARENT_ID")
    private String parentId;
    //    @ApiModelProperty(value = "下级菜单集合")
    private List<UserOrgVO> children;

}