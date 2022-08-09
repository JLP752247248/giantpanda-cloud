package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.ContractMapper;
import com.panda.auth.user.entity.Contract;
import com.panda.auth.user.service.ContractService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper mapper;

    @Override
    public int insert(Contract data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<Contract> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(Contract data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(Contract data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<Contract> dataCollection) {
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
    public Contract selectByPrimaryKey(Long userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Contract> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<Contract> listSelective(Contract query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(Contract data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long userId) {
        return mapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int update(Contract data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public Contract detail(Long userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Contract> listQuery() {
        return mapper.listQuery();
    }
}