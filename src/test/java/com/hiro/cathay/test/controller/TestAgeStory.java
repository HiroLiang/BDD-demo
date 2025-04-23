package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;

public class TestAgeStory extends TestStory {

    public TestAgeStory() {
        // no other same name story path
        super("**/test-age.story", new TestAgeSteps());
    }

}
