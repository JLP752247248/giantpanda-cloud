package com.panda.auth.dtos;

import com.panda.auth.user.entity.PermInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PermissionDTO implements java.io.Serializable {

    private Long id;
    private String permission;
    private Integer enabled;
    private String note;

    public PermissionDTO(PermInfo permission) {
        this.id = permission.getId();
        this.permission = permission.getPermission();
        this.enabled = permission.getEnabled();
        this.note = permission.getNote();
    }

    public boolean isEnabled(){
        return this.enabled == 1 ;
    }
}
