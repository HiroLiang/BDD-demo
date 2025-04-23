package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;

public class TestAgeStory extends TestStory {

    public TestAgeStory() {
        super("**/test-age.story", new TestAgeRedirector());
    }

}
