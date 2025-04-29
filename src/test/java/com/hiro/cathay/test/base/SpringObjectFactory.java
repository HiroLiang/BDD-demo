package com.hiro.cathay.test.base;

import com.hiro.cathay.test.CathayTestApplication;
import io.cucumber.core.backend.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SpringObjectFactory implements ObjectFactory {

    private static ConfigurableApplicationContext context;
    private final Map<Class<?>, Object> instances = new HashMap<>();

    @Override
    public void start() {
        if (context == null) {
            context = SpringApplication.run(CathayTestApplication.class);
        }
    }

    @Override
    public void stop() {
        context.close();
        context = null;
    }

    @Override
    public boolean addClass(Class<?> stepClass) {
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return context.getBean(type);
    }

}
