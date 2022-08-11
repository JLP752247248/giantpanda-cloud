package com.panda.auth.service;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  09:30
 * @Description: TODO
 * @Version: 1.0
 */
public interface IdService {
    /**
     * 获取下一个id
     * @param key
     * @return
     */
    Long getNextId(String key);

    /**
     * 批量获取id
     * @param key id值
     * @param step id个数
     * @return 返回最大的那个id，其他id一次递减
     */
    Long getNextIdBatch(String key, int step);


}
