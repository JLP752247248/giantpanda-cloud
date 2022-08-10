package com.panda.auth.dtos;



import com.panda.auth.user.entity.UserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    public UserDTO() {
        // empty constructor
        roles = new ArrayList<>();
        permissions = new ArrayList<>();
    }

    public UserDTO(UserInfo user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();

            this.birthDate = user.getBirthDate();



            this.note = user.getNote();





            // Because the permissions can be associated to more than one roles i'm creating two String arrays
            // with the distinct keys of roles and permissions.
            roles = new ArrayList<>();
            permissions = new ArrayList<>();

        }
    }

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String gender;
    private Date birthDate;

    private Byte enabled;

    private String note;

    private Date creationDt;
    private Date updatedDt;
    private Date loginDt;

    private Byte secured;

    private ContactDTO contactDTO;
    private AddressDTO addressDTO;

    // permissions and roles list
    private List<String> roles;
    private List<String> permissions;

}
