package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.SpringConcordionRunner;
import org.junit.runner.RunWith;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(SpringConcordionRunner.class)
public class AgeTest {

    private final WebClient webClient = WebClient.create("http://localhost:8080");

    public String getResult(String str) {
        return webClient.get()
                .uri("/test/age?age=" + str)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
