package com.panda.auth.user.dao;

import com.panda.auth.user.entity.Addr;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddrMapper {
    /**
     * 单条插入
     */
    int insert(@Param("data") Addr data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(@Param("dataCollection") Collection<Addr> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(@Param("data") Addr data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(@Param("data") Addr data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(@Param("dataCollection") Collection<Addr> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(@Param("userId") Long userId);

    /**
     * id集合删除
     */
    int deleteByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 主键查询
     */
    Addr selectByPrimaryKey(@Param("userId") Long userId);

    /**
     * id集合查询
     */
    List<Addr> selectByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<Addr> listSelective(@Param("query") Addr query);

    /**
     * 列表查询
     */
    List<Addr> listQuery();
}