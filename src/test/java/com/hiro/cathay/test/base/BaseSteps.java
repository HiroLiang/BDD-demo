package com.hiro.cathay.test.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseSteps {

    protected <T> List<T> toList(List<Map<String, String>> rows, Class<T> clazz) {
        try {
            List<T> list = new ArrayList<>();
            for (Map<String, String> row : rows) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (Map.Entry<String, String> entry : row.entrySet()) {
                    String fieldName = entry.getKey();
                    String value = entry.getValue();

                    String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                    for (Method method : clazz.getMethods()) {
                        if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                            Class<?> paramType = method.getParameterTypes()[0];
                            Object convertedValue = convert(value, paramType);
                            method.invoke(instance, convertedValue);
                            break;
                        }
                    }
                }
                list.add(instance);
            }
            return list;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Convert class: " + clazz.getSimpleName() + " failed.", e);
        }
    }

    private Object convert(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + targetType.getName());
        }
    }

}
