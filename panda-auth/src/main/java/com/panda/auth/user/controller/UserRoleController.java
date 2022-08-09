package com.panda.auth.user.controller;

import com.panda.auth.user.entity.UserRole;
import com.panda.auth.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/userRole")
@Api(tags = "")
public class UserRoleController {
    @Autowired
    private UserRoleService service;

    @PutMapping(value = "/insert")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insert", notes = "")
    public int insert(@RequestBody UserRole data) {
        return service.insert(data);
    }

    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<UserRole> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody UserRole data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody UserRole data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<UserRole> dataCollection) {
        return service.updateBatch(dataCollection);
    }

    @DeleteMapping(value = "/deleteByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByPrimaryKey", notes = "")
    public int deleteByPrimaryKey(@RequestParam(value = "userId") Long userId, @RequestParam(value = "roleId") Long roleId) {
        return service.deleteByPrimaryKey(userId, roleId);
    }

    @GetMapping(value = "/selectByPrimaryKey")
    @ApiOperation(value = "selectByPrimaryKey", notes = "")
    public UserRole selectByPrimaryKey(@RequestParam(value = "userId") Long userId, @RequestParam(value = "roleId") Long roleId) {
        return service.selectByPrimaryKey(userId, roleId);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<UserRole> listSelective(@RequestParam(value = "query") UserRole query) {
        return service.listSelective(query);
    }

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增", notes = "")
    public int add(@RequestBody UserRole data) {
        return service.add(data);
    }

    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "删除", notes = "")
    public int delete(@RequestParam(value = "userId") Long userId, @RequestParam(value = "roleId") Long roleId) {
        return service.delete(userId, roleId);
    }

    @PostMapping(value = "/update")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "修改", notes = "")
    public int update(@RequestBody UserRole data) {
        return service.update(data);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情", notes = "")
    public UserRole detail(@RequestParam(value = "userId") Long userId, @RequestParam(value = "roleId") Long roleId) {
        return service.detail(userId, roleId);
    }

    @GetMapping(value = "/listQuery")
    @ApiOperation(value = "列表查询", notes = "")
    public List<UserRole> listQuery() {
        return service.listQuery();
    }
}