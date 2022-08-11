
package com.panda.auth.vo;

import com.panda.auth.user.entity.PermInfo;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  13:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PermInfoVo  {

    /***/
    @NotNull
    private Long id;
    /***/
    @NotNull
    private String permission;
    /***/
    @NotNull
    private Integer enabled;
    /***/
    @NotNull
    private String note;

    public PermInfo buildPermInfo(){
        PermInfo tempPermInfo =new PermInfo();
            /***/
           tempPermInfo.setId(id);
            /***/
           tempPermInfo.setPermission(permission);
            /***/
           tempPermInfo.setEnabled(enabled);
            /***/
           tempPermInfo.setNote(note);
        return tempPermInfo;
    }
}


