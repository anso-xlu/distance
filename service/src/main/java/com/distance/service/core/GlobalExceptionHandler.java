package com.distance.service.core;

import com.alibaba.fastjson.JSON;
import com.distance.service.common.constants.ECode;
import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.common.exception.ResultException;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * customize response RuntimeException
     */
    @ExceptionHandler(ResultException.class)
    public Result respMsgException(HttpServletResponse response, ResultException e) {
        response.setStatus(e.getResult().getStatus());
        return e.getResult();
    }

    /**
     * 数据请求解析错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result httpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e) {
        logError(request, e, null);
        String msg = e.getMessage();

        // Enum 值不存在
        if (msg.startsWith("JSON parse error: parse enum")) {
            String name = msg.substring(msg.lastIndexOf("$") + 1, msg.lastIndexOf(" error"));
            String value = msg.substring(msg.lastIndexOf(": ") + 1);
            return Wrapper.result(null, ECode.G_0001,
                    String.format("%s: %s=%s", ECode.G_0001.getMsg(), name.toLowerCase(), value));
        }

        if (e.getCause() instanceof JsonParseException) {
            return Wrapper.error(ECode.G_0003);
        }
        return Wrapper.error(ECode.G_0004);
    }

    /**
     * request method 错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result httpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logError(request, e, null);
        return Wrapper.error(ECode.G_0005);
    }

    /**
     * 参数校验错误
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result methodArgumentNotValidException(HttpServletRequest request, BindException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        //完整的错误信息,页面只返回一个
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        fieldErrors.forEach(fieldError -> {
            sb.append(",");
            sb.append("{" + fieldError.getField() + ":" + fieldError.getDefaultMessage() + "}");
        });
        sb.append("]");

        logError(request, null, sb.toString());
        return Wrapper.result(null, ECode.G_0001, fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        logError(request, e, null);
        return Wrapper.error(ECode.G_404);
    }

    /**
     * Exception 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        logError(request, e, null);
        return Wrapper.error(ECode.G_500);
    }

    /**
     * log
     */
    private void logError(HttpServletRequest request, Exception e, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(",url: ");
        sb.append(request.getRequestURL());
        sb.append(",param: ");
        sb.append(JSON.toJSONString(request.getParameterMap()));
        sb.append(", message: ");
        sb.append(msg);

        if (e != null) log.error(sb.toString(), e);
        else log.error(sb.toString());
    }
}
