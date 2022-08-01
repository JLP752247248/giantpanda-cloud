package com.panda.auth.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="skype")
    private String skype;

    @Column(name="facebook")
    private String facebook;

    @Column(name="linkedin")
    private String linkedin;

    @Column(name = "website")
    private String website;

    @Column(name="note")
    private String note;

    @OneToOne
    @MapsId
    private User user;

}
