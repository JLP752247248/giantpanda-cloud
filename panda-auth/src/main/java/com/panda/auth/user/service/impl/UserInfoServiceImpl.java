package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.UserInfoMapper;
import com.panda.auth.user.entity.UserInfo;
import com.panda.auth.user.service.UserInfoService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.panda.auth.util.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper mapper;

    private final static String USERINFO_ID_SEQ_KEY = "user_key";

    @Autowired
    private IdService idService;

    @Override
    public UserInfo insert(UserInfo data) {
        Long id = idService.getNextId(USERINFO_ID_SEQ_KEY);
        data.setId(id);
        int res = mapper.insert(data);
        UserInfo resObj = res > 0 ? mapper.selectByPrimaryKey(id) : null;
        return resObj;
    }

    @Override
    public int insertBatch(Collection<UserInfo> dataCollection) {
        AtomicReference<Long> seq = new AtomicReference<>(idService.getNextIdBatch(USERINFO_ID_SEQ_KEY, dataCollection.size()));
        dataCollection.forEach(data -> {
            data.setId(seq.getAndSet(seq.get() - 1));
        });
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(UserInfo data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<UserInfo> dataCollection) {
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
    public UserInfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<UserInfo> listSelective(UserInfo query) {
        return mapper.listSelective(query);
    }
}