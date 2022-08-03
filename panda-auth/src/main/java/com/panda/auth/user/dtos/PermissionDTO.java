package com.panda.auth.user.dtos;

import com.panda.auth.user.entities.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PermissionDTO implements java.io.Serializable {

    private Long id;
    private String permission;
    private Integer enabled;
    private String note;

    public PermissionDTO(Permission permission) {
        this.id = permission.getId();
        this.permission = permission.getPermission();
        this.enabled = permission.getEnabled();
        this.note = permission.getNote();
    }

    public boolean isEnabled(){
        return this.enabled == 1 ;
    }
}
