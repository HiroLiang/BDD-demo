package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;
import com.hiro.cathay.test.TestStoryConfig;
import net.thucydides.core.annotations.Step;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

@TestStoryConfig(
        storyPaths = "**/test-plus.story",
        stepsClass = TestPlusStory.TestPlusSteps.class
)
public class TestPlusStory extends TestStory {

    public static class TestPlusSteps {

        private int num1;
        private int num2;

        @Given("給一個數字 <num1>")
        @Step("給一個數字 {0}")
        public void givenNum1(int num1) {
            this.num1 = num1;
        }

        @When("給第二個數字 <num2>")
        @Step("給第二個數字 {0}")
        public void givenNum2(int num2) {
            this.num2 = num2;
        }

        @Then("取得結果: <expected>")
        @Step("取得結果: {0}")
        public void expectSum(int expected) {
            Assert.assertEquals(expected, num1 + num2);
        }

    }

}
