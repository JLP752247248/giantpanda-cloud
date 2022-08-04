package com.panda.common.mvc;

import lombok.Data;

@Data
public class Response<T> {
    /**
     * 消息
     */
    private String msg;
    /**
     * 状态码
     */
    private int code;
    /**
     * 结果数据
     */
    private T data;
    /**
     * 调用链id
     */
    private String traceId;
    /**
     * 是否成功
     */
    private boolean success;

    private Response(T data) {
        this.data = data;
    }

    public static <V> Response<V> createSuc(V data) {
        Response<V> response = new Response<V>(data);
        response.setMsg("成功");
        response.setCode(200);
        response.setSuccess(true);
        return response;
    }

    public static <V> Response<V> createSuc(V data,String msg) {
        Response<V> response = new Response<V>(data);
        response.setMsg(msg);
        response.setCode(200);
        response.setSuccess(true);
        return response;
    }

    public static <V> Response<V> createErr(V data) {
        Response<V> response = new Response<V>(data);
        response.setMsg("失败");
        response.setCode(500);
        response.setSuccess(true);
        return response;
    }
}
