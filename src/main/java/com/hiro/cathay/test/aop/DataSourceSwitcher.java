package com.hiro.cathay.test.aop;

import com.hiro.cathay.test.annotation.UseDataSource;
import com.hiro.cathay.test.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class DataSourceSwitcher {

    @Around("@annotation(com.hiro.cathay.test.annotation.UseDataSource)")
    public Object switchDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        UseDataSource useDataSource = getAnnotation(joinPoint);

        assert useDataSource != null;

        if (DataSourceContextHolder.isHolding() && !DataSourceContextHolder.isSame(useDataSource.source())) {
            throw new Exception("Can't switch datasource from " + useDataSource.source());
        } else {
            DataSourceContextHolder.set(useDataSource.source());
        }

        Object result = null;

        try {
            result = joinPoint.proceed();
        } finally {
            if (!DataSourceContextHolder.isHolding() || result instanceof Exception) {
                DataSourceContextHolder.clear();
            } else {
                DataSourceContextHolder.remove();
            }
        }
        return result;
    }

    private UseDataSource getAnnotation(ProceedingJoinPoint joinPoint) {
        UseDataSource anno = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(UseDataSource.class);

        if (Objects.isNull(anno)) {
            anno = joinPoint.getTarget().getClass().getAnnotation(UseDataSource.class);
        }

        return anno;
    }

}
