package com.hiro.cathay.test;

import net.serenitybdd.jbehave.SerenityStories;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TestStory extends SerenityStories {

    private static volatile ConfigurableApplicationContext context;

    private static volatile boolean shutdownHookAdded = false;

    private final List<String> storyPath;

    private final List<Object> testClass;

    private final boolean activateSpring;

    protected TestStory() {
        // If Config is missing, throw IllegalArgumentException
        TestStoryConfig config = this.getClass().getAnnotation(TestStoryConfig.class);
        if (config == null)
            throw new IllegalArgumentException("Missing @StoryConfig on " + this.getClass().getName());

        // Init Spring Context if needed
        this.activateSpring = config.activateSpring();
        init();

        // Get Story Path and Step Class
        this.storyPath = Arrays.asList(config.storyPaths());
        this.testClass = Arrays.stream(config.stepsClass())
                .map(this::instantiate)
                .collect(Collectors.toList());
    }

    private Object instantiate(Class<?> clazz) {
        try {
            // Get Constructor and Parameters
            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            Parameter[] parameters = constructor.getParameters();
            Object[] args = new Object[parameters.length];

            // Get Arguments if needed
            for (int i = 0; i < parameters.length; i++) {
                Parameter param = parameters[i];
                Class<?> paramType = param.getType();

                Qualifier qualifier = param.getAnnotation(Qualifier.class);

                if (qualifier != null) { // Use Qualifier if exists
                    String beanName = qualifier.value();
                    args[i] = context.getBean(beanName, paramType);
                } else { // No Qualifier, try to find bean by type
                    Map<String, ?> candidates = context.getBeansOfType(paramType);
                    if (candidates.isEmpty()) {
                        throw new RuntimeException("No bean found for type: " + paramType.getName());
                    } else if (candidates.size() > 1) {
                        throw new RuntimeException("Multiple beans found for type: " + paramType.getName() +
                                ". Use @Qualifier to specify which one to use.");
                    }
                    args[i] = candidates.values().iterator().next();
                }
            }

            // Create instance
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate step class: " + clazz.getName(), e);
        }
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), this.testClass.toArray());
    }

    @Override
    public List<String> storyPaths() {
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromClass(this.getClass()),
                this.storyPath,
                null
        );
    }

    /**
     * Init Spring Context if needed.
     */
    private void init() {
        if (context == null && activateSpring) {
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
