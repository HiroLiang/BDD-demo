package com.hiro.cathay.test.controller;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.web.reactive.function.client.WebClient;

public class TestAgeSteps {

    private String input;
    private String response;

    @Given("給一個字 <str>")
    public void givenStr(String str) {
        this.input = str;
    }

    @When("呼叫 API")
    public void callApi() {
        WebClient client = WebClient.create("http://localhost:8080");
        this.response = client.get()
                .uri("/age?age=" + input)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Then("取得結果: <expected>")
    public void printResult(String expected) {
        Assert.assertEquals(expected, this.response);
    }

}
