package com.distance.service.common.exception;

import com.distance.service.common.model.Result;
import lombok.Getter;

/**
 * customize response RuntimeException
 */
@Getter
public class ResultException extends RuntimeException{
    private Result result;

    public ResultException(Result result) {
        this.result = result;
    }

}
