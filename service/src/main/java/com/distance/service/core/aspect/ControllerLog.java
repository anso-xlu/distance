package com.distance.service.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *  打印接口 请求/响应参数, 消耗时间
 */
@Aspect
@Component
@Slf4j
public class ControllerLog {
    @Pointcut("execution(public * com.distance.service..*.*Controller.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        if (HttpMethod.GET.matches(request.getMethod())) {
            sb.append(request.getQueryString());
        } else {
            for (Object arg : joinPoint.getArgs())
                sb.append(arg);
        }

        log.info("REQUEST_URL: " + request.getRequestURL().toString());
        log.info("REQUEST_METHOD: " + request.getMethod());
        log.info("REQUEST_ARGS: " + sb.toString());

        // 执行目标方法
        Object o = joinPoint.proceed();

        log.info("RESPONSE_TIME: " + (System.currentTimeMillis() - startTime));
        log.info("RESPONSE_ARGS: " + o);
        return o;
    }
}
