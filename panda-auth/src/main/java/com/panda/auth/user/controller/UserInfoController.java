package com.panda.auth.user.controller;

import com.panda.auth.user.entity.UserInfo;
import com.panda.auth.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/userInfo")
@Api(tags = "")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "add", notes = "")
    public UserInfo insert(@RequestBody UserInfo data) {
        return service.insert(data);
    }

    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<UserInfo> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody UserInfo data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody UserInfo data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<UserInfo> dataCollection) {
        return service.updateBatch(dataCollection);
    }

    @DeleteMapping(value = "/deleteByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByPrimaryKey", notes = "")
    public int deleteByPrimaryKey(@RequestParam(value = "id") Long id) {
        return service.deleteByPrimaryKey(id);
    }

    @DeleteMapping(value = "/deleteByIdCollection")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByIdCollection", notes = "")
    public int deleteByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.deleteByIdCollection(idCollection);
    }

    @GetMapping(value = "/selectByPrimaryKey")
    @ApiOperation(value = "selectByPrimaryKey", notes = "")
    public UserInfo selectByPrimaryKey(@RequestParam(value = "id") Long id) {
        return service.selectByPrimaryKey(id);
    }

    @GetMapping(value = "/selectByIdCollection")
    @ApiOperation(value = "selectByIdCollection", notes = "")
    public List<UserInfo> selectByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.selectByIdCollection(idCollection);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<UserInfo> listSelective(@RequestParam(value = "query") UserInfo query) {
        return service.listSelective(query);
    }
}