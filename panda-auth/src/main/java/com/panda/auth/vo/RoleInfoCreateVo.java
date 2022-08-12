
package com.panda.auth.vo;

import com.panda.auth.user.entity.RoleInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  13:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@ApiModel
public class RoleInfoCreateVo  {

    /***/
    @NotNull
    @ApiModelProperty(name="id", notes="", example="")
    private Long id;
    /**角色名称*/
    @NotNull
    @ApiModelProperty(name="name", notes="角色名称", example="")
    private String name;
    /**状态 0停用 1启用*/
    @NotNull
    @ApiModelProperty(name="status", notes="状态 0停用 1启用", example="")
    private Short status;
    /**父角色id*/
    @NotNull
    @ApiModelProperty(name="parentId", notes="父角色id", example="")
    private Long parentId;
    /**角色描述*/
    @NotNull
    @ApiModelProperty(name="desc", notes="角色描述", example="")
    private String desc;

    public RoleInfo buildRoleInfo(){
        RoleInfo tempRoleInfo =new RoleInfo();
        /***/
        tempRoleInfo.setId(id);
        /**角色名称*/
        tempRoleInfo.setName(name);
        /**状态 0停用 1启用*/
        tempRoleInfo.setStatus(status);
        /**父角色id*/
        tempRoleInfo.setParentId(parentId);
        /**角色描述*/
        tempRoleInfo.setDesc(desc);
        return tempRoleInfo;
    }
}


