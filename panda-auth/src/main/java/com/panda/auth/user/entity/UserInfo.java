package com.panda.auth.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@ApiModel
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.id
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="id", notes="", example="", position=100)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.username
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="username", notes="用户名", example="用户名", position=100)
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.password
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="password", notes="密码，密文", example="密码，密文", position=100)
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.phone
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="phone", notes="手机号", example="", position=100)
    private Long phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.email
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="email", notes="邮箱", example="邮箱", position=100)
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.birth_date
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="birthDate", notes="", example="2022-08-10 15:10:40", position=100)
    private Date birthDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.status
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="status", notes="状态：1 有效  2 注销 3 禁用", example="", position=100)
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.create_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="createTime", notes="", example="2022-08-10 15:10:40", position=100)
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.update_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="updateTime", notes="", example="2022-08-10 15:10:40", position=100)
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.login_dt
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="loginDt", notes="", example="2022-08-10 15:10:40", position=100)
    private Date loginDt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.note
     *
     * @mbg.generated
     */
    @ApiModelProperty(name="note", notes="", example="note", position=100)
    private String note;
}