package com.panda.auth.user.service;

import com.panda.auth.user.entity.Addr;
import java.util.Collection;
import java.util.List;

public interface AddrService {
    /**
     * 单条插入
     */
    int insert(Addr data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<Addr> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(Addr data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(Addr data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<Addr> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * id集合删除
     */
    int deleteByIdCollection(Collection<Long> idCollection);

    /**
     * 主键查询
     */
    Addr selectByPrimaryKey(Long userId);

    /**
     * id集合查询
     */
    List<Addr> selectByIdCollection(Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<Addr> listSelective(Addr query);

    /**
     * 新增
     */
    int add(Addr data);

    /**
     * 删除
     */
    int delete(Long userId);

    /**
     * 修改
     */
    int update(Addr data);

    /**
     * 详情
     */
    Addr detail(Long userId);

    /**
     * 列表查询
     */
    List<Addr> listQuery();
}