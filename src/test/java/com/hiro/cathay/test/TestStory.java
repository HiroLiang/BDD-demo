package com.hiro.cathay.test;

import net.serenitybdd.jbehave.SerenityStories;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.List;

public abstract class TestStory extends SerenityStories {

    private static volatile ConfigurableApplicationContext context;

    private static volatile boolean shutdownHookAdded = false;

    private final String storyPath;

    private final Object testClass;

    protected TestStory(String storyPath, Object testClass) {
        init();
        this.storyPath = storyPath;
        this.testClass = testClass;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), this.testClass);
    }

    @Override
    public List<String> storyPaths() {
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromClass(this.getClass()),
                Collections.singletonList(this.storyPath),
                null
        );
    }

    private void init() {
        if (context == null) {
            synchronized (TestStory.class) {
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
