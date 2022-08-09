package com.panda.auth.user.controller;

import com.panda.auth.user.entity.Contract;
import com.panda.auth.user.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contract")
@Api(tags = "")
public class ContractController {
    @Autowired
    private ContractService service;

    @PutMapping(value = "/insert")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insert", notes = "")
    public int insert(@RequestBody Contract data) {
        return service.insert(data);
    }

    @PutMapping(value = "/insertBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "insertBatch", notes = "")
    public int insertBatch(@RequestBody Collection<Contract> dataCollection) {
        return service.insertBatch(dataCollection);
    }

    @PostMapping(value = "/updateByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKey", notes = "")
    public int updateByPrimaryKey(@RequestBody Contract data) {
        return service.updateByPrimaryKey(data);
    }

    @PostMapping(value = "/updateByPrimaryKeySelective")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateByPrimaryKeySelective", notes = "")
    public int updateByPrimaryKeySelective(@RequestBody Contract data) {
        return service.updateByPrimaryKeySelective(data);
    }

    @PostMapping(value = "/updateBatch")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "updateBatch", notes = "")
    public int updateBatch(@RequestBody Collection<Contract> dataCollection) {
        return service.updateBatch(dataCollection);
    }

    @DeleteMapping(value = "/deleteByPrimaryKey")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByPrimaryKey", notes = "")
    public int deleteByPrimaryKey(@RequestParam(value = "userId") Long userId) {
        return service.deleteByPrimaryKey(userId);
    }

    @DeleteMapping(value = "/deleteByIdCollection")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "deleteByIdCollection", notes = "")
    public int deleteByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.deleteByIdCollection(idCollection);
    }

    @GetMapping(value = "/selectByPrimaryKey")
    @ApiOperation(value = "selectByPrimaryKey", notes = "")
    public Contract selectByPrimaryKey(@RequestParam(value = "userId") Long userId) {
        return service.selectByPrimaryKey(userId);
    }

    @GetMapping(value = "/selectByIdCollection")
    @ApiOperation(value = "selectByIdCollection", notes = "")
    public List<Contract> selectByIdCollection(@RequestParam(value = "idCollection") Collection<Long> idCollection) {
        return service.selectByIdCollection(idCollection);
    }

    @GetMapping(value = "/listSelective")
    @ApiOperation(value = "listSelective", notes = "")
    public List<Contract> listSelective(@RequestParam(value = "query") Contract query) {
        return service.listSelective(query);
    }

    @PutMapping(value = "/add")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "新增", notes = "")
    public int add(@RequestBody Contract data) {
        return service.add(data);
    }

    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "删除", notes = "")
    public int delete(@RequestParam(value = "userId") Long userId) {
        return service.delete(userId);
    }

    @PostMapping(value = "/update")
    @Transactional(rollbackFor = Throwable.class)
    @ApiOperation(value = "修改", notes = "")
    public int update(@RequestBody Contract data) {
        return service.update(data);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情", notes = "")
    public Contract detail(@RequestParam(value = "userId") Long userId) {
        return service.detail(userId);
    }

    @GetMapping(value = "/listQuery")
    @ApiOperation(value = "列表查询", notes = "")
    public List<Contract> listQuery() {
        return service.listQuery();
    }
}