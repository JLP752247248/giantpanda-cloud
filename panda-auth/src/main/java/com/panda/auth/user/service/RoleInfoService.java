package com.panda.auth.user.service;

import com.panda.auth.user.entity.RoleInfo;
import java.util.Collection;
import java.util.List;

public interface RoleInfoService {
    /**
     * 单条插入
     */
    int insert(RoleInfo data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<RoleInfo> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(RoleInfo data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(RoleInfo data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<RoleInfo> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long id);

    /**
     * id集合删除
     */
    int deleteByIdCollection(Collection<Long> idCollection);

    /**
     * 主键查询
     */
    RoleInfo selectByPrimaryKey(Long id);

    /**
     * id集合查询
     */
    List<RoleInfo> selectByIdCollection(Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<RoleInfo> listSelective(RoleInfo query);

    /**
     * 新增
     */
    int add(RoleInfo data);

    /**
     * 删除
     */
    int delete(Long id);

    /**
     * 修改
     */
    int update(RoleInfo data);

    /**
     * 详情
     */
    RoleInfo detail(Long id);

    /**
     * 列表查询
     */
    List<RoleInfo> listQuery();

    /**
     * 查询当前登陆用户可以查看的所有角色
     */
    List<RoleInfo> queryAllRolesBySecurity();
}