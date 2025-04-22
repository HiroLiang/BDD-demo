package com.hiro.cathay.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/name")
    public Mono<String> testName(@RequestParam String name) {
        return Mono.just("Hello " + name);
    }

    @RequestMapping("/age")
    public Mono<String> testAge(@RequestParam int age) {
        return Mono.just("Age is " + age);
    }

}
