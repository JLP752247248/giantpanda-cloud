package com.panda.auth.user.service;

import com.panda.auth.user.entity.PermRole;
import java.util.Collection;
import java.util.List;

public interface PermRoleService {
    /**
     * 单条插入
     */
    int insert(PermRole data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<PermRole> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(PermRole data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(PermRole data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<PermRole> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long permissionId, Long roleId);

    /**
     * 主键查询
     */
    PermRole selectByPrimaryKey(Long permissionId, Long roleId);

    /**
     * 动态查询
     */
    List<PermRole> listSelective(PermRole query);

    /**
     * 新增
     */
    int add(PermRole data);

    /**
     * 删除
     */
    int delete(Long permissionId, Long roleId);

    /**
     * 修改
     */
    int update(PermRole data);

    /**
     * 详情
     */
    PermRole detail(Long permissionId, Long roleId);

    /**
     * 列表查询
     */
    List<PermRole> listQuery();
}