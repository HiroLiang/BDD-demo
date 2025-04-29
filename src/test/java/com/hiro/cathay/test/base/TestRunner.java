package com.hiro.cathay.test.base;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.hiro.cathay.test.controller",
        plugin = {"pretty"}
)
public class TestRunner {
}
