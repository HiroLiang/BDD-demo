package com.hiro.cathay.test.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("testService")
public class TestService {

    public void howl() {
        System.out.println("Woof Woof Woof!");
    }

}
