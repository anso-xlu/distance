package com.distance.service.common.wrapper;

import com.distance.service.common.constants.ECode;
import com.distance.service.common.model.Result;

/**
 * 对应 model Result
 */
public class Wrapper {
    public static Result ok() {
        return Wrapper.result(null, ECode.G_200, null);
    }

    public static Result ok(Object data) {
        return Wrapper.result(data, ECode.G_200, null);
    }

    public static Result error(ECode eCode) {
        return Wrapper.result(null, eCode, null);
    }
    public static Result error(Object data, ECode eCode) {
        return Wrapper.result(data, eCode, null);
    }

    public static Result result(Object data, ECode eCode, String msg) {
        if (msg == null) msg = eCode.getMsg();
        return new Result(data, eCode.getStatus().value(), eCode.getCode(), msg);
    }

}
