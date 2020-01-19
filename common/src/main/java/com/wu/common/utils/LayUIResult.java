/**
 * @program: kefu
 * @description: layui响应结果json
 * @author: Wu
 * @create: 2019-11-16 10:30
 **/
package com.wu.common.utils;

import java.io.Serializable;

public class LayUIResult implements Serializable {

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public LayUIResult() {
    }

    public static LayUIResult build(Integer code, Integer count, String msg, Object data) {
        return new LayUIResult(code, count, msg, data);
    }

    public static LayUIResult build(Integer status, String msg, Object data) {
        return new LayUIResult(status, msg, data);
    }

    public static LayUIResult ok(Integer count, Object data) {
        return new LayUIResult(0, count, "success", data);
    }

    public static LayUIResult fail(Integer count, Object data) {
        return new LayUIResult(1, count, "fail", data);
    }

    public static LayUIResult fail() {
        return new LayUIResult(1, 0, "fail", null);
    }

    public static LayUIResult fail(String msg) {
        return new LayUIResult(1, 0, msg, null);
    }

    public static LayUIResult ok(Object data) {
        return new LayUIResult(data);
    }

    public static LayUIResult ok() {
        return new LayUIResult(null);
    }

    public static LayUIResult build(Integer code, String msg) {
        return new LayUIResult(code, msg, null);
    }

    public LayUIResult(Integer code, Integer count, String msg, Object data) {
        this.count = count;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayUIResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayUIResult(Object data) {
        this.code = 0;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
