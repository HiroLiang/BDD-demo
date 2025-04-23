package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;

public class TestNameStory extends TestStory {

    public TestNameStory() {
        super("**/test-name.story", new TestNameRedirector());
    }

}
