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

    public static <T> Response<T> createSuc(T data) {
        Response<T> response = new Response<T>(data);
        response.setMsg("成功");
        response.setCode(200);
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> createSuc(T data,String msg) {
        Response<T> response = new Response<T>(data);
        response.setMsg(msg);
        response.setCode(200);
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> createErr(T data) {
        Response<T> response = new Response<T>(data);
        response.setMsg("失败");
        response.setCode(500);
        response.setSuccess(true);
        return response;
    }
}
