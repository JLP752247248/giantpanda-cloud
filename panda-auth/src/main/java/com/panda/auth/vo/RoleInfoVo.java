
package com.panda.auth.vo;

import com.panda.auth.user.entity.RoleInfo;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  13:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class RoleInfoVo  {

    /***/
    @NotNull
    private Long id;
    /***/
    @NotNull
    private String role;

    public RoleInfo buildRoleInfo(){
        RoleInfo tempRoleInfo =new RoleInfo();
            /***/
           tempRoleInfo.setId(id);
            /***/
           tempRoleInfo.setRole(role);
        return tempRoleInfo;
    }
}


