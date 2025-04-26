package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.TestStory;
import com.hiro.cathay.test.TestStoryConfig;

@TestStoryConfig(
        storyPaths = "**/test-age.story",
        stepsClass = TestAgeSteps.class,
        activateSpring = true
)
public class TestAgeStory extends TestStory {
}
