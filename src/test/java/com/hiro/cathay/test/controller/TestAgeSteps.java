package com.hiro.cathay.test.controller;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.springframework.web.reactive.function.client.WebClient;

public class TestAgeSteps {

    private String input;
    private String response;

    @Step("給一個字 {0}")
    public void givenStr(String str) {
        this.input = str;
    }

    @Step("呼叫 API")
    public void callApi() {
        WebClient client = WebClient.create("http://localhost:8080");
        this.response = client.get()
                .uri("/test/age?age=" + input)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Step("取得結果: {0}")
    public void printResult(String expected) {
        Assert.assertEquals(expected, this.response);
    }

}
