package com.hiro.cathay.test.controller;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TestNameRedirector {

    TestNameSteps testNameSteps = new TestNameSteps();

    @Given("給一個字 <str>")
    public void givenStr(String str) {
        testNameSteps.givenStr(str);
    }

    @When("呼叫 API")
    public void callApi() {
        testNameSteps.callApi();
    }

    @Then("取得結果: <expected>")
    public void printResult(String expected) {
        testNameSteps.printResult(expected);
    }

}
