package com.db2020prj.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvalidTokenException extends Exception {
    @JsonProperty
    String msg;
    @JsonProperty
    int code;

    public InvalidTokenException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
