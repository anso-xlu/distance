package com.distance.service.common.model;

import com.distance.service.common.constants.ECode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class Result<T> {
    private T data;
    private int status;
    private int code;
    private String msg;

    public Result(T data, @NotNull ECode eCode, String msg) {
        this.data = data;
        this.status = eCode.getStatus().value();
        this.code = eCode.getCode();
        this.msg = msg != null ? msg : eCode.getMsg();
    }
}
