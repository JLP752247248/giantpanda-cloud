package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.PermInfoMapper;
import com.panda.auth.user.entity.PermInfo;
import com.panda.auth.user.service.PermInfoService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermInfoServiceImpl implements PermInfoService {
    @Autowired
    private PermInfoMapper mapper;

    @Override
    public int insert(PermInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<PermInfo> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(PermInfo data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(PermInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<PermInfo> dataCollection) {
        return mapper.updateBatch(dataCollection);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIdCollection(Collection<Long> idCollection) {
        return mapper.deleteByIdCollection(idCollection);
    }

    @Override
    public PermInfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PermInfo> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<PermInfo> listSelective(PermInfo query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(PermInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(PermInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public PermInfo detail(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PermInfo> listQuery() {
        return mapper.listQuery();
    }
}