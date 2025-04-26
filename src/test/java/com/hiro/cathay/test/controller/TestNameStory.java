package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;
import com.hiro.cathay.test.TestStoryConfig;

@TestStoryConfig(
        storyPaths = "com/hiro/cathay/test/controller/test-name.story",
        stepsClass = TestNameSteps.class,
        activateSpring = true
)
public class TestNameStory extends TestStory {
}
