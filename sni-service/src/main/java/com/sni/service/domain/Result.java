package com.sni.service.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable  {

    private static final long serialVersionUID = 6551938393090269438L;

    private Integer code;

    private String msg;

    private Object data;

    public Result() {

    }

    public Result data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(0, "成功");
    }

    public static Result success(Object data) {
        return new Result(0, "成功", data);
    }

    public static Result error() {
        return new Result(1, "失败");
    }

    public static Result error(String msg) {
        return new Result(1, msg);
    }
}
