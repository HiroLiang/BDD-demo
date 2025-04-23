package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;

public class TestNameStory extends TestStory {

    public TestNameStory() {
        // Defined the story path clearly (Meta filter is not effective anymore)
        super("com/hiro/cathay/test/controller/test-name.story", new TestNameRedirector());
    }

}
