package com.panda.auth.user.dao;

import com.panda.auth.user.entity.UserRole;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    /**
     * 单条插入
     */
    int insert(@Param("data") UserRole data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(@Param("dataCollection") Collection<UserRole> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(@Param("data") UserRole data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(@Param("data") UserRole data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(@Param("dataCollection") Collection<UserRole> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 主键查询
     */
    UserRole selectByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 动态查询
     */
    List<UserRole> listSelective(@Param("query") UserRole query);

    /**
     * 列表查询
     */
    List<UserRole> listQuery();
}