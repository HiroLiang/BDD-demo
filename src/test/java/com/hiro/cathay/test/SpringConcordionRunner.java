package com.hiro.cathay.test;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runners.model.InitializationError;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringConcordionRunner extends ConcordionRunner {

    private static volatile ConfigurableApplicationContext context;
    private static boolean shutdownHookAdded = false;

    /**
     * 用 ShutdownHook 綁 JVM 線程，維持 Spring 啟動狀態
     */
    public SpringConcordionRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        if (context == null) {
            synchronized (SpringConcordionRunner.class) {
                if (context == null) {
                    context = SpringApplication.run(CathayTestApplication.class);
                    if (!shutdownHookAdded) {
                        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                            if (context != null) {
                                context.close();
                            }
                        }));
                        shutdownHookAdded = true;
                    }
                }
            }
        }
    }
}
