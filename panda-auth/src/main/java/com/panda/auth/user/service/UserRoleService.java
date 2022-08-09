package com.panda.auth.user.service;

import com.panda.auth.user.entity.UserRole;
import java.util.Collection;
import java.util.List;

public interface UserRoleService {
    /**
     * 单条插入
     */
    int insert(UserRole data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<UserRole> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(UserRole data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(UserRole data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<UserRole> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long userId, Long roleId);

    /**
     * 主键查询
     */
    UserRole selectByPrimaryKey(Long userId, Long roleId);

    /**
     * 动态查询
     */
    List<UserRole> listSelective(UserRole query);

    /**
     * 新增
     */
    int add(UserRole data);

    /**
     * 删除
     */
    int delete(Long userId, Long roleId);

    /**
     * 修改
     */
    int update(UserRole data);

    /**
     * 详情
     */
    UserRole detail(Long userId, Long roleId);

    /**
     * 列表查询
     */
    List<UserRole> listQuery();
}