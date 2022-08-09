package com.panda.auth.user.controller;

import com.panda.auth.user.entity.PermInfo;
import com.panda.auth.user.service.PermInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/permInfo")
@Api(tags = "")
public class PermInfoController {
    @Autowired
    private PermInfoService service;

    @PutMapping(value = "/insert")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insert", notes = "")
    public int insert(@RequestBody PermInfo data) {
        return service.insert(data);
    }

    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<PermInfo> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody PermInfo data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody PermInfo data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<PermInfo> dataCollection) {
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
    public PermInfo selectByPrimaryKey(@RequestParam(value = "id") Long id) {
        return service.selectByPrimaryKey(id);
    }

    @GetMapping(value = "/selectByIdCollection")
    @ApiOperation(value = "selectByIdCollection", notes = "")
    public List<PermInfo> selectByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.selectByIdCollection(idCollection);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<PermInfo> listSelective(@RequestParam(value = "query") PermInfo query) {
        return service.listSelective(query);
    }

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增", notes = "")
    public int add(@RequestBody PermInfo data) {
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
    public int update(@RequestBody PermInfo data) {
        return service.update(data);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情", notes = "")
    public PermInfo detail(@RequestParam(value = "id") Long id) {
        return service.detail(id);
    }

    @GetMapping(value = "/listQuery")
    @ApiOperation(value = "列表查询", notes = "")
    public List<PermInfo> listQuery() {
        return service.listQuery();
    }
}