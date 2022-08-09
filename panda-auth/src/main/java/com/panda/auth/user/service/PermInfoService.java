package com.panda.auth.user.service;

import com.panda.auth.user.entity.PermInfo;
import java.util.Collection;
import java.util.List;

public interface PermInfoService {
    /**
     * 单条插入
     */
    int insert(PermInfo data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<PermInfo> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(PermInfo data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(PermInfo data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<PermInfo> dataCollection);

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
    PermInfo selectByPrimaryKey(Long id);

    /**
     * id集合查询
     */
    List<PermInfo> selectByIdCollection(Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<PermInfo> listSelective(PermInfo query);

    /**
     * 新增
     */
    int add(PermInfo data);

    /**
     * 删除
     */
    int delete(Long id);

    /**
     * 修改
     */
    int update(PermInfo data);

    /**
     * 详情
     */
    PermInfo detail(Long id);

    /**
     * 列表查询
     */
    List<PermInfo> listQuery();
}