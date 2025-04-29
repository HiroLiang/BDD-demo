package com.hiro.cathay.test.controller;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class TestPlusSteps {

    private int a;
    private int b;
    private int sum;

    @Given("two numbers {int} and {int}")
    public void two_numbers(int num1, int num2) {
        a = num1;
        b = num2;
    }

    @When("I add them")
    public void i_add_them() {
        sum = a + b;
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expected) {
        assertEquals(expected, sum);
    }

}
