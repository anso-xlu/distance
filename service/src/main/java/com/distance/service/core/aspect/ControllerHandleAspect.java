package com.distance.service.core.aspect;

import com.distance.service.common.constants.Constant;
import com.distance.service.common.model.ResultPage;
import com.distance.service.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 1. 设置 response httpStatus
 * 2. jpa page.size 从0开始, 传入-1,返回+1
 */
@Aspect
@Component
@Slf4j
public class ControllerHandleAspect {

    @Pointcut("execution(public * com.distance.service..*.*Controller.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String method = request.getMethod();
        if (HttpMethod.GET.matches(method)) {
            Integer pageNumber = (Integer) request.getAttribute(Constant.PAGE_NUMBER_NAME);
            if (pageNumber == null) {
                request.setAttribute(Constant.PAGE_NUMBER_NAME, Constant.DEFAULT_PAGE_NUMBER);
            } else {
                request.setAttribute(Constant.PAGE_NUMBER_NAME, pageNumber - 1);
            }
            if (request.getAttribute(Constant.PAGE_SIZE_NAME) == null) {
                request.setAttribute(Constant.PAGE_SIZE_NAME, Constant.DEFAULT_PAGE_SIZE);
            }
        }

        // 执行目标方法
        Object o = joinPoint.proceed();

        if (o instanceof Result) {
            Result result = (Result) o;

            //修改返回的HttpStatus
            if (result.getStatus() == 200 && (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method))) {
                sra.getResponse().setStatus(HttpStatus.CREATED.value());
            } else {
                sra.getResponse().setStatus(result.getStatus());
            }

            // Jpa Page.page 从0开始, 返回时需要 + 1
            if (result.getData() instanceof Page) {
                Page page = (Page) result.getData();
                o = new Result<>(new ResultPage<>(page), result.getStatus(), result.getCode(), result.getMsg());
            }
        }
        return o;
    }
}
