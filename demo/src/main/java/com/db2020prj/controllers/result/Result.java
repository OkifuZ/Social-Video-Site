package com.db2020prj.controllers.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result<T>{
    @JsonProperty
    private T data;
    @JsonProperty
    private int code;
    @JsonProperty
    private String msg;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "";
    }

    public int getCode() { return code; }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg; }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
