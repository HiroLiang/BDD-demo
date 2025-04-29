package com.hiro.cathay.test.controller;

import com.hiro.cathay.test.base.BaseSteps;
import com.hiro.cathay.test.model.AnimalData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSumSteps extends BaseSteps {

    List<AnimalData> animals;
    private int total = 0;

    @Given("the following animals:")
    public void the_following_animals(DataTable dataTable) {
        this.animals = toList(dataTable.asMaps(String.class, String.class), AnimalData.class);
    }

    @When("I sum the animal counts")
    public void i_sum_the_animal_counts() {
        total = animals.stream()
                .mapToInt(AnimalData::getCount)
                .sum();
    }

    @Then("the total should be {int}")
    public void the_total_should_be(Integer expectedTotal) {
        assertEquals(expectedTotal.intValue(), total);
    }

}
