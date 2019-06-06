package com.distance.service.common.support;

import com.distance.service.common.constants.ECode;
import com.distance.service.common.exception.ResultException;
import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * 验证类
 * @param <T>
 */
@Component
@Slf4j
public class Validator<T> {
    @Resource
    private javax.validation.Validator validator;

    /**
     *  验证出错误, 抛出异常 GlobalExceptionHandler处理
     * @param object 验证类
     * @param groups    分组
     * @throws ResultException
     */
    public void validate(T object, Class<?>... groups) throws ResultException {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        if (validate != null && validate.size() != 0) {
            StringBuilder sb = new StringBuilder();
            validate.forEach(e -> sb.append(e.getMessage()));
            log.error(sb.toString());

            Result result = Wrapper.result(null, ECode.G_0001, validate.iterator().next().getMessage());
            throw new ResultException(result);
        }
    }

    /**
     * ------------------- groups --------------------------
     */
    public interface save {
    }

    public interface get {
    }

    public interface delete {
    }
}
