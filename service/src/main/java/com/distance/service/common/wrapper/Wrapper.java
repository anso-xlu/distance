package com.distance.service.common.wrapper;

import com.distance.service.common.constants.ECode;
import com.distance.service.common.model.PageWrapper;
import com.distance.service.common.model.Result;
import org.springframework.data.domain.Page;

/**
 * 对应 model Result
 */
public class Wrapper {
    public static <T> Result<T> ok() {
        return new Result<>(null, ECode.G_200, null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data, ECode.G_200, null);
    }

    public static <T> Result<T> error(ECode eCode) {
        return new Result<>(null, eCode, null);
    }

    public static <T> Result<T> error(ECode eCode, String msg) {
        return new Result<>(null, eCode, msg);
    }

    public static <T> Result<T> error(T data, ECode eCode) {
        return new Result<>(data, eCode, null);
    }

    public static <T> Result<PageWrapper<T>> ok(Page<T> page) {
        PageWrapper<T> pageWrapper = new PageWrapper<>(page.getContent(), page.getSize(), page.getNumber() + 1,
                page.hasNext(), page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements());
        return new Result<>(pageWrapper, ECode.G_200, null);
    }

}
