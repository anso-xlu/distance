package com.distance.service.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class Result<T> {
    private T data;
    @JsonIgnore
    private int status;
    private int code;
    private String msg;

    public Result(T data, int status, int code, String msg) {
        this.data = data;
        this.status = status;
        this.code = code;
        this.msg = msg;
    }
}
