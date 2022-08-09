package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.AddrMapper;
import com.panda.auth.user.entity.Addr;
import com.panda.auth.user.service.AddrService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddrServiceImpl implements AddrService {
    @Autowired
    private AddrMapper mapper;

    @Override
    public int insert(Addr data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<Addr> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(Addr data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(Addr data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<Addr> dataCollection) {
        return mapper.updateBatch(dataCollection);
    }

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return mapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int deleteByIdCollection(Collection<Long> idCollection) {
        return mapper.deleteByIdCollection(idCollection);
    }

    @Override
    public Addr selectByPrimaryKey(Long userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Addr> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<Addr> listSelective(Addr query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(Addr data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long userId) {
        return mapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int update(Addr data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public Addr detail(Long userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Addr> listQuery() {
        return mapper.listQuery();
    }
}