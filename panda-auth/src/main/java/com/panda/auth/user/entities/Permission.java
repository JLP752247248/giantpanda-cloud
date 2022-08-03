package com.panda.auth.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    public Permission(Long id, String permission) {
        this.id = id;
        this.permission = permission;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="permission", nullable = false)
    private String permission;

    // enabled as default
    @Column(name="enabled",columnDefinition = "TINYINT(1)")
    private Integer enabled = 1;

    @Column(name="note" )
    private String note;
    public boolean isEnabled(){
        return this.enabled == 1 ;
    }
}
