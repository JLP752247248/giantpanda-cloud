package com.panda.auth.user.controller;

import com.panda.auth.user.entity.RoleInfo;
import com.panda.auth.user.service.RoleInfoService;
import com.panda.auth.vo.RoleInfoCreateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roleInfo")
@Api(tags = "角色管理服务")
public class RoleInfoController {
    @Autowired
    private RoleInfoService service;

    @PutMapping(value = "/create")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增角色", notes = "")
    public int insert(@RequestBody @Validated RoleInfoCreateVo data) {
        return service.insert(data.buildRoleInfo());
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列举所有角色（当前用户权限范围内）", notes = "")
    public List<RoleInfo> listSelective() {
        return service.listQuery();
    }


    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<RoleInfo> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody RoleInfo data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody RoleInfo data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<RoleInfo> dataCollection) {
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
    public RoleInfo selectByPrimaryKey(@RequestParam(value = "id") Long id) {
        return service.selectByPrimaryKey(id);
    }

    @GetMapping(value = "/selectByIdCollection")
    @ApiOperation(value = "selectByIdCollection", notes = "")
    public List<RoleInfo> selectByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.selectByIdCollection(idCollection);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<RoleInfo> listSelective(@RequestParam(value = "query") RoleInfo query) {
        return service.listSelective(query);
    }

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增", notes = "")
    public int add(@RequestBody RoleInfo data) {
        return service.add(data);
    }

    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "删除", notes = "")
    public int delete(@RequestParam(value = "id") Long id) {
        return service.delete(id);
    }

    @PostMapping(value = "/update")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "修改", notes = "")
    public int update(@RequestBody RoleInfo data) {
        return service.update(data);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情", notes = "")
    public RoleInfo detail(@RequestParam(value = "id") Long id) {
        return service.detail(id);
    }

    @GetMapping(value = "/listQuery")
    @ApiOperation(value = "列表查询", notes = "")
    public List<RoleInfo> listQuery() {
        return service.listQuery();
    }
}