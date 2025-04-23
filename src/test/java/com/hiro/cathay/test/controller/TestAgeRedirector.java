package com.hiro.cathay.test.controller;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TestAgeRedirector {

    public TestAgeSteps testAgeSteps = new TestAgeSteps();

    @Given("給一個字 <str>")
    public void givenStr(String str) {
        testAgeSteps.givenStr(str);
    }

    @When("呼叫 API")
    public void callApi() {
        testAgeSteps.callApi();
    }

    @Then("取得結果: <expected>")
    public void printResult(String expected) {
        testAgeSteps.printResult(expected);
    }

}
