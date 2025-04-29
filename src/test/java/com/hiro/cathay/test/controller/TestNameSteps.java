package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.base.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestNameSteps extends BaseSteps {

    @Autowired
    WebTestClient client;

    private String name;

    private String response;

    @Given("你的名字是: {string}")
    public void getName(String name) {
        this.name = name;
    }

    @When("呼叫 api")
    public void callApi() {
        response = client.get()
                .uri(uriBuilder -> uriBuilder.path("/test/name").queryParam("name", name).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Then("取得回應: {string}")
    public void checkResponse(String response) {
        Assert.assertEquals(response, this.response);
    }
}
