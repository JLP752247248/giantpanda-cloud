package com.panda.olap.serivce;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "business")
public interface ServiceATFeign  {

    /**
     * 调用at服务插入记录
     *
     * @return String
     */
    @GetMapping(value = "/insert-at")
    String insertAT();
}