package com.panda.auth.service;

import com.panda.auth.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  09:30
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class SeqTableIdService implements IdService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Long min;

    private Long max;

    private Long defaultStep = 5000L;

    @Override
    public Long getNextId(String key) {

        return getNextIdBatch(key, 1);
    }

    @Override
    public Long getNextIdBatch(String key, int step) {
        return getNextIdBatchFromDb(key, step);
    }


    @Transactional
    public Long getNextIdBatchFromDb(String key, int step) {
        //这里为了保证不出现幻读，使用先update后查询
        int res = jdbcTemplate.update("update sys_sequence set seq_value = seq_value + ?  where seq_key = ?", step, key);
        if (res <= 0) {
            throw new RuntimeException("获取主键值失败");
        }
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select seq_name,seq_value from sys_sequence where seq_key = ?", key);
        if (Objects.isNull(mapList) || mapList.isEmpty()) {
            throw new RuntimeException("当前key未建立主键表记录");
        }
        return (Long) mapList.get(0).get("seq_value");
    }


}
