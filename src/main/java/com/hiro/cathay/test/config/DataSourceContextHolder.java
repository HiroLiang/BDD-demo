package com.hiro.cathay.test.config;

import java.util.ArrayDeque;
import java.util.Deque;

public class DataSourceContextHolder {

    private static final ThreadLocal<Deque<DatabaseSource>> contextHolder = ThreadLocal.withInitial(ArrayDeque::new);

    public static void set(DatabaseSource dataSourceKey) {
        contextHolder.get().addFirst(dataSourceKey);
    }

    public static DatabaseSource get() {
        Deque<DatabaseSource> stack = contextHolder.get();
        if (stack.isEmpty()) {
           return DatabaseSource.DB1;
        }
        return stack.peekFirst();
    }

    public static void remove() {
        Deque<DatabaseSource> stack = contextHolder.get();
        if (stack.isEmpty()) {
            return;
        }
        stack.removeFirst();
    }

    public static void clear() {
        contextHolder.get().clear();
    }

    public static boolean isHolding() {
        return !contextHolder.get().isEmpty();
    }

    public static boolean isSame(DatabaseSource dataSourceKey) {
        Deque<DatabaseSource> stack = contextHolder.get();
        if (stack.isEmpty()) {
            throw new IllegalStateException("Try to get datasource from empty context");
        }
        return stack.peekFirst() == dataSourceKey;
    }

}
