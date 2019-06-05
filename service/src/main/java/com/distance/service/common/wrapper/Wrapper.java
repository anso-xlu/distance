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
        return new Result(data,
                eCode.getStatus().value(),
                Integer.valueOf(eCode.name().split("_")[1]),
                msg != null ? msg : eCode.getMsg());
    }

}
