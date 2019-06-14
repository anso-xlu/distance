package com.distance.service.common.support;

import com.distance.service.common.constants.ECode;
import com.distance.service.common.exception.ResultException;
import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 验证类
 * @param <T>
 */
@Component
@Slf4j
public class Checker<T> {
    @Resource
    private Validator validator;

    /**
     *  验证出错误, 抛出异常, GlobalExceptionHandler处理
     * @param object 验证类
     * @param groups    分组
     * @throws ResultException
     */
    public void check(@NotNull T object, Class<?>... groups) throws ResultException {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        if (validate != null && validate.size() != 0) {
            Result result = Wrapper.error(ECode.G_0001, validate.iterator().next().getMessage());
            throw new ResultException(result);
        }
    }

    public void createCheck(T object) throws ResultException {
        check(object, Create.class);
    }

    public void updateCheck(T object) throws ResultException {
        check(object, Update.class);
    }

    public void selectCheck(T object) throws ResultException {
        check(object, Select.class);
    }

    public void deleteCheck(T object) throws ResultException {
        check(object, Delete.class);
    }

    /**
     * ------------------- groups --------------------------
     */
    public interface Select {
    }

    public interface Create {
    }

    public interface Update {
    }

    public interface Delete {
    }
}
