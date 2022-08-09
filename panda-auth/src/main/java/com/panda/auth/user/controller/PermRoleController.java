package com.panda.auth.user.controller;

import com.panda.auth.user.entity.PermRole;
import com.panda.auth.user.service.PermRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/permRole")
@Api(tags = "")
public class PermRoleController {
    @Autowired
    private PermRoleService service;

    @PutMapping(value = "/insert")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insert", notes = "")
    public int insert(@RequestBody PermRole data) {
        return service.insert(data);
    }

    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<PermRole> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody PermRole data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody PermRole data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<PermRole> dataCollection) {
        return service.updateBatch(dataCollection);
    }

    @DeleteMapping(value = "/deleteByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByPrimaryKey", notes = "")
    public int deleteByPrimaryKey(@RequestParam(value = "permissionId") Long permissionId, @RequestParam(value = "roleId") Long roleId) {
        return service.deleteByPrimaryKey(permissionId, roleId);
    }

    @GetMapping(value = "/selectByPrimaryKey")
    @ApiOperation(value = "selectByPrimaryKey", notes = "")
    public PermRole selectByPrimaryKey(@RequestParam(value = "permissionId") Long permissionId, @RequestParam(value = "roleId") Long roleId) {
        return service.selectByPrimaryKey(permissionId, roleId);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<PermRole> listSelective(@RequestParam(value = "query") PermRole query) {
        return service.listSelective(query);
    }

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增", notes = "")
    public int add(@RequestBody PermRole data) {
        return service.add(data);
    }

    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "删除", notes = "")
    public int delete(@RequestParam(value = "permissionId") Long permissionId, @RequestParam(value = "roleId") Long roleId) {
        return service.delete(permissionId, roleId);
    }

    @PostMapping(value = "/update")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "修改", notes = "")
    public int update(@RequestBody PermRole data) {
        return service.update(data);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情", notes = "")
    public PermRole detail(@RequestParam(value = "permissionId") Long permissionId, @RequestParam(value = "roleId") Long roleId) {
        return service.detail(permissionId, roleId);
    }

    @GetMapping(value = "/listQuery")
    @ApiOperation(value = "列表查询", notes = "")
    public List<PermRole> listQuery() {
        return service.listQuery();
    }
}