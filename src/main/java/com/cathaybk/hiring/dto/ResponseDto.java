package com.cathaybk.hiring.dto;

public class ResponseDto<T> {

    private Integer ok;
    private Integer code;
    private String msg;
    private T data;

    public Integer getOk() {
        return ok;
    }

    public ResponseDto<T> setOk(Integer ok) {
        this.ok = ok;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseDto<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDto<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }
}
