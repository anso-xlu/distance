package com.distance.service.core.aspect;

import com.distance.service.common.support.Checker;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class CheckerSaveAspect {
    @Resource
    private Checker checker;

    @Pointcut("execution(public * org.springframework.data.jpa.repository.save*(java.lang.Object))")
    public void checkSaveAspect() {
    }

    @Before("checkSaveAspect()")
    public void doBefore(ProceedingJoinPoint joinPoint) {
        Object dto = joinPoint.getArgs()[0];
        try {
            Method getId = dto.getClass().getMethod("getId");
            Object id = getId.invoke(dto);
            if (id == null) {
                checker.createCheck(dto);
            } else {
                checker.updateCheck(dto);
            }
        } catch (NoSuchMethodException e) {
            log.warn(dto.getClass().getName() + "not getId()");
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("getId() error", e);
        }
    }
}
