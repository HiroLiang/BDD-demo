package com.hiro.cathay.test.annotation;

import com.hiro.cathay.test.config.DatabaseSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,  ElementType.METHOD})
public @interface UseDataSource {
    DatabaseSource source() default DatabaseSource.DB1;
}
